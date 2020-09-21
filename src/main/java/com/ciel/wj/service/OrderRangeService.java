package com.ciel.wj.service;

import com.ciel.wj.dao.OrderRangeDAO;
import com.ciel.wj.dto.OrderRangeDTO;
import com.ciel.wj.pojo.OrderRange;
import com.ciel.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderRangeService {
    @Autowired
    OrderRangeDAO orderRangeDAO;
    @Autowired
    UserService userService;
    @Autowired
    MemberService memberService;
    @Autowired
    ShootRangeService shootRangeService;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return orderRangeDAO.findAll(PageRequest.of(page,size,sort));
    }

    public List<OrderRangeDTO> list(){
        List<OrderRange> orderRanges = orderRangeDAO.findAll();
        return this.convertToDTOs(orderRanges);
    }

//    public List<OrderRangeDTO> listBySubmitUserId(int uid){
//        List<OrderRange> orderRanges = orderRangeDAO.findAllBySubmitUser(userService.findById(uid));
//        return this.convertToDTOs(orderRanges);
//    }

    public List<OrderRangeDTO> convertToDTOs(List<OrderRange> or) {
//        List<OrderRangeDTO> orderRangeDTOS = or
//                .stream().map(orderRange -> (OrderRangeDTO) new OrderRangeDTO().convertFrom(orderRange)).collect(Collectors.toList());
//        orderRangeDTOS.forEach(o -> {
//            OrderRange orderRange = orderRangeDAO.findById(o.getId());
//            o.setMemberNameZh(orderRange.getMember().getNameZh());
//            o.setShootRangeName(orderRange.getShootRange().getName());
//            o.setSubmitUserName(orderRange.getSubmitUser().getName());
//            o.setVerifyUserName(orderRange.getVerifyUser().getName());
//        });
        List<OrderRangeDTO> orderRangeDTOS = or
                .stream().map(orderRange -> new OrderRangeDTO(orderRange)).collect(Collectors.toList());

        return orderRangeDTOS;
    }

//    public Page list(int page, int size) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        return orderRangeDAO.findAll(PageRequest.of(page,size,sort));
//    }

    public Page<OrderRangeDTO> listDTOs(int page, int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Page<OrderRange> orderRanges = orderRangeDAO.findAll(PageRequest.of(page,size,sort));
        Page<OrderRangeDTO> orderRangeDTOPage = new PageImpl<OrderRangeDTO>(convertToDTOs(orderRanges.getContent()),PageRequest.of(page,size,sort),orderRangeDAO.findAll().size());
        return  orderRangeDTOPage;
    }

    public Page<OrderRangeDTO> listBySubmitUsername(String username, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        User user = userService.findByUsername(username);
        Page<OrderRange> orderRanges = orderRangeDAO.findAllBySubmitUid(user.getId(),PageRequest.of(page,size,sort));

//        OrderRange orderRange = new OrderRange();
//        orderRange.setSubmitUser(user);
//        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id","scheduleDatetime","submitDatetime","verifyDatetime","member","verifyUser");
//        Example<OrderRange> example = Example.of(orderRange,matcher);
//        Page<OrderRange> orderRanges = orderRangeDAO.findAll(example,PageRequest.of(page, size,sort));

//        List<OrderRangeDTO> orderRangeDTOList = convertToDTOs(orderRanges.getContent());
        Page<OrderRangeDTO> orderRangeDTOPage = new PageImpl<OrderRangeDTO>(convertToDTOs(orderRanges.getContent()),PageRequest.of(page,size,sort),orderRangeDAO.findAllBySubmitUser(user).size());
        return  orderRangeDTOPage;

//        Specification<OrderRange> specification = new Specification<OrderRange>() {
//            @Override
//            public Predicate toPredicate(Root<OrderRange> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                Path<Object> submitUser = root.get("submitUser");
//                Path<Object> submit_uid = submitUser.get("id");
//                Predicate predicate = criteriaBuilder.equal(submit_uid, user.getId());
//                return predicate;
//            }
//        };
//        Page<OrderRange> orderRanges = orderRangeDAO.findAll(specification,PageRequest.of(page, size,sort) );
//        return orderRanges;
    }

    public Page<OrderRangeDTO> queryByMemberName(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Page<OrderRange> orderRanges = orderRangeDAO.findAllByMemberName(name,PageRequest.of(page,size,sort));
        Page<OrderRangeDTO> orderRangeDTOPage = new PageImpl<OrderRangeDTO>(convertToDTOs(orderRanges.getContent()),PageRequest.of(page,size,sort),orderRangeDAO.findAllByMemberName(name).size());
        return  orderRangeDTOPage;

    }

    public Page<OrderRangeDTO> queryBySubmitUserName(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Page<OrderRange> orderRanges = orderRangeDAO.findAllBySubmitUserName(name,PageRequest.of(page,size,sort));
        Page<OrderRangeDTO> orderRangeDTOPage = new PageImpl<OrderRangeDTO>(convertToDTOs(orderRanges.getContent()),PageRequest.of(page,size,sort),orderRangeDAO.findAllBySubmitUserName(name).size());
        return  orderRangeDTOPage;

    }

    public void verify(OrderRange orderRange) {
        orderRange.setStatus(1);
    }

    public void delete(int id) {
        orderRangeDAO.deleteById(id);
    }

    public void addOrUpdate(OrderRange orderRange) { orderRangeDAO.save(orderRange); }

    public void addOrUpdate(OrderRangeDTO orderRangeDTO,String userName) {
        OrderRange orderRange = new OrderRange();
        //非创建订单，获取订单ID
        if (! "".equals(orderRangeDTO.getId())) {
            orderRange.setId(orderRangeDTO.getId());
        }
        //无审核用户，先设置为提交用户，否则为空会报错
        if (! "".equals(orderRangeDTO.getVerifyUserName())) {
            orderRange.setVerifyUser(userService.findByUsername(userName));
        }
        orderRange.setMember(memberService.findById(orderRangeDTO.getMemberId()));
        orderRange.setShootRange(shootRangeService.findById(orderRangeDTO.getShootRangeId()));
        orderRange.setSubmitUser(userService.findByUsername(userName));
        orderRange.setScheduleDatetime(orderRangeDTO.getScheduleDatetime());
        orderRange.setSubmitDatetime(new Date(System.currentTimeMillis()));
        orderRange.setStatus(0);
        orderRangeDAO.save(orderRange);
    }


}
