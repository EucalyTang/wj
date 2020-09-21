package com.ciel.wj.controller;

import com.ciel.wj.pojo.Member;
import com.ciel.wj.pojo.User;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.MemberService;
import com.ciel.wj.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    UserService userService;

    @GetMapping("api/admin/member/{size}/{page}")
    public Page listMembers(@PathVariable("size") int size, @PathVariable("page") int page) {
        return memberService.list(page-1, size);
    }

    @GetMapping("api/member/{size}/{page}")
    public Page listInviteMembers(@PathVariable("size") int size, @PathVariable("page") int page) {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(userName);
        String inviteCode = user.getInviteCode();
        return memberService.listByUserInviteCode(inviteCode,page-1,size);
    }

    @GetMapping("api/member/byuser")
    public List<Member> getUserMembers() {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(userName);
        String inviteCode = user.getInviteCode();
        return memberService.listByUserInviteCode(inviteCode);
    }

    @PutMapping("api/admin/member/modify")
    public Result updateMembers(@RequestBody Member member) {
        memberService.addOrUpdate(member);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @PostMapping("api/admin/member/add")
    public Result addMembers(@RequestBody Member member) {
        String passportNumber = member.getPassportNumber();
        //过滤HTML
        passportNumber = HtmlUtils.htmlEscape(passportNumber);
        member.setPassportNumber(passportNumber);
        member.setEnabled(true);

        boolean isExist = memberService.isExist(passportNumber);
        if (isExist) {
            String message = "该护照号已注册";
            return ResultFactory.buildFailResult(message);
        }

        if (null == member.getMemberNumber()) {
            memberService.addOrUpdate(member);
            int id = member.getId();
            //生成6位会员编号
            String memberNumber = generateNumber(6, id);
            member.setMemberNumber(memberNumber);
        }

        memberService.addOrUpdate(member);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @DeleteMapping("api/admin/member/{id}")
    public Result deleteMember(@PathVariable("id") int id) {
        memberService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @DeleteMapping("api/member/{id}")
    public Result deleteMemberByMember(@PathVariable("id") int id) {
        Member member = memberService.findById(id);
        if (member.isEnabled()) {
            return ResultFactory.buildFailResult("删除失败");
        } else {
            memberService.delete(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }
    }

    @GetMapping("api/member/{id}")
    public Member getOnemember(@PathVariable("id") int id) {
        return memberService.findById(id);
    }

    @PostMapping("api/member/register")
    public Result registerMembers(@RequestBody Member member) {
        String passportNumber = member.getPassportNumber();
        //过滤HTML
        passportNumber = HtmlUtils.htmlEscape(passportNumber);
        member.setPassportNumber(passportNumber);
        //用户注册完成后需经审核通过才启用账号
        member.setEnabled(false);

        boolean isExist = memberService.isExist(passportNumber);
        if (isExist) {
            String message = "该护照号已注册";
            return ResultFactory.buildFailResult(message);
        }

        memberService.addOrUpdate(member);
        return ResultFactory.buildSuccessResult("注册成功");
    }

    @PutMapping("api/admin/member/enable/{id}")
    public Result enableMembers(@PathVariable("id") int id) {
        Member member = memberService.findById(id);
        if (null == member.getMemberNumber()) {
            //生成6位会员编号
            String memberNumber = generateNumber(6, id);
            member.setMemberNumber(memberNumber);
        }
        if (null == member.getPassword()) {
            //生成8位会员初始密码
            String memberPwd = getRandomString(8);
            member.setPassword(memberPwd);
        }
        member.setEnabled(true);
        memberService.addOrUpdate(member);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //管理界面上传护照图片
    @CrossOrigin
    @PostMapping("api/admin/member/passportimgs")
    public String passportUpload(MultipartFile file) throws Exception {
        String folder = "./img";
        //File imageFolder = new File(folder);
        //file.transferTo()方法调用时如果参数是相对路径则会使用temp目录，为父目录
        File imageFolder = new File(new File(folder).getAbsolutePath());
        String fileName = getRandomString(6) + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
        File f = new File(imageFolder, fileName);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURL = "http://bang.t.v3x.cn/image/" + fileName;
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    //用户注册界面上传护照图片
    @CrossOrigin
    @PostMapping("api/member/passportimg")
    public String passportUploadBase64(@RequestBody Map file) throws Exception {
        String folder = "./img";
        String base64Str = file.get("content").toString();
        File imageFolder = new File(folder);
        String fileName = getRandomString(6);
        //fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
        if (base64Str.indexOf("data:image/png;") != -1) {
            base64Str = base64Str.replace("data:image/png;base64,", "");
            fileName += ".png";
        } else if (base64Str.indexOf("data:image/jpeg;") != -1) {
            base64Str = base64Str.replace("data:image/jpeg;base64,", "");
            fileName += ".jpeg";
        }
        File f = new File(imageFolder, fileName);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }

        byte[] fileBytes = Base64.getDecoder().decode(base64Str);
        try {
            FileUtils.writeByteArrayToFile(f, fileBytes);
            String imgURL = "http://bang.t.v3x.cn/image/" + fileName;
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    //生成V开头，长度为length，数字为id的编号
    public String generateNumber(int length, int id) {
        String number = "V";
        String s = String.valueOf(id);
        for (int i = s.length(); length - 1 - i >= 0; i++) {
            number += "0";
        }
        number += s;
        return number;
    }
}
