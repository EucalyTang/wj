package com.ciel.wj.service;

import com.ciel.wj.dao.OrderEquipDAO;
import com.ciel.wj.dto.OrderEquipDTO;
import com.ciel.wj.pojo.Discount;
import com.ciel.wj.pojo.OrderEquip;
import com.ciel.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderEquipService {
    @Autowired
    OrderEquipDAO orderEquipDAO;
    @Autowired
    UserService userService;
    @Autowired
    MemberService memberService;
    @Autowired
    GunService gunService;
    @Autowired
    BulletService bulletService;
    @Autowired
    DiscountService discountService;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return orderEquipDAO.findAll(PageRequest.of(page,size,sort));
    }

    public List<OrderEquipDTO> list(){
        List<OrderEquip> orderEquips = orderEquipDAO.findAll();
        return this.convertToDTOs(orderEquips);
    }

    public List<OrderEquipDTO> convertToDTOs(List<OrderEquip> oe) {
        List<OrderEquipDTO> orderEquipDTOS = oe
                .stream().map(orderEquip -> new OrderEquipDTO(orderEquip)).collect(Collectors.toList());

        return orderEquipDTOS;
    }

    public Page<OrderEquipDTO> listDTOs(int page, int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Page<OrderEquip> orderEquips = orderEquipDAO.findAll(PageRequest.of(page,size,sort));
        Page<OrderEquipDTO> orderEquipDTOPage = new PageImpl<OrderEquipDTO>(convertToDTOs(orderEquips.getContent()),PageRequest.of(page,size,sort),orderEquipDAO.findAll().size());
        return  orderEquipDTOPage;
    }

    public Page<OrderEquipDTO> listBySubmitUsername(String username, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        User user = userService.findByUsername(username);
        Page<OrderEquip> orderEquips = orderEquipDAO.findAllBySubmitUid(user.getId(),PageRequest.of(page,size,sort));

        Page<OrderEquipDTO> orderEquipDTOPage = new PageImpl<OrderEquipDTO>(convertToDTOs(orderEquips.getContent()),PageRequest.of(page,size,sort),orderEquipDAO.findAllBySubmitUser(user).size());
        return  orderEquipDTOPage;
    }

    public Page<OrderEquipDTO> queryByMemberName(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Page<OrderEquip> orderEquips = orderEquipDAO.findAllByMemberName(name,PageRequest.of(page,size,sort));
        Page<OrderEquipDTO> orderEquipDTOPage = new PageImpl<OrderEquipDTO>(convertToDTOs(orderEquips.getContent()),PageRequest.of(page,size,sort),orderEquipDAO.findAllByMemberName(name).size());
        return  orderEquipDTOPage;
    }

    public Page<OrderEquipDTO> queryBySubmitUserName(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Page<OrderEquip> orderEquips = orderEquipDAO.findAllBySubmitUserName(name,PageRequest.of(page,size,sort));
        Page<OrderEquipDTO> orderEquipDTOPage = new PageImpl<OrderEquipDTO>(convertToDTOs(orderEquips.getContent()),PageRequest.of(page,size,sort),orderEquipDAO.findAllBySubmitUserName(name).size());
        return  orderEquipDTOPage;
    }

    public void verify(OrderEquip orderEquip) {
        orderEquip.setStatus(1);
    }

    public void changePrice(OrderEquip orderEquip, double price) {
        orderEquip.setFinalTotal(price);
    }

    public void delete(int id) {
        orderEquipDAO.deleteById(id);
    }

    public void addOrUpdate(OrderEquip orderEquip) { orderEquipDAO.save(orderEquip); }

    public void addOrUpdate(OrderEquipDTO orderEquipDTO,String userName) {
        OrderEquip orderEquip = new OrderEquip();
        //非创建订单，获取订单ID
        if (! "".equals(orderEquipDTO.getId())) {
            orderEquip.setId(orderEquipDTO.getId());
        }
        //无审核用户，先设置为提交用户，否则为空会报错
        if (! "".equals(orderEquipDTO.getVerifyUserName())) {
            orderEquip.setVerifyUser(userService.findByUsername(userName));
        }
        Discount discount = discountService.findById(1);
        double gunAmount = orderEquipDTO.getGunQuantity() * gunService.findById(orderEquipDTO.getGunId()).getPrice();
        double bulletAmount = orderEquipDTO.getBulletQuantity() * bulletService.findById(orderEquipDTO.getBulletId()).getPrice();
        double discountAmount = discount.getBulletDiscountValue() + discount.getGunDiscountValue();
        orderEquip.setMember(memberService.findById(orderEquipDTO.getMemberId()));
        orderEquip.setGun(gunService.findById(orderEquipDTO.getGunId()));
        orderEquip.setBullet(bulletService.findById(orderEquipDTO.getBulletId()));
        orderEquip.setGunQuantity(orderEquipDTO.getGunQuantity());
        orderEquip.setBulletQuantity(orderEquipDTO.getBulletQuantity());
        orderEquip.setGunAmount(gunAmount);
        orderEquip.setBulletAmount(bulletAmount);
        orderEquip.setSubmitUser(userService.findByUsername(userName));
        orderEquip.setScheduleDatetime(orderEquipDTO.getScheduleDatetime());
        orderEquip.setSubmitDatetime(new Date(System.currentTimeMillis()));
        orderEquip.setStatus(0);

        orderEquip.setDiscount(discount);
        orderEquip.setOriginTotal(gunAmount+bulletAmount);
        orderEquip.setFinalTotal(gunAmount+bulletAmount-discountAmount);
        orderEquipDAO.save(orderEquip);
    }
}
