package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pojo.Customer;
import pojo.Message;
import pojo.ResponseResult;
import service.PersonService;
import service.exception.UserNoLoginException;
import utils.UploadFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @program: homeplus
 * @description:
 * @author: ZEK
 * @create: 2019-04-15 12:25
 **/
@Controller
@RequestMapping("/person")
public class PersonController extends BaseController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/selectCustomer")
    @ResponseBody
    public ResponseResult<Customer> selectCustomer (
            HttpSession session
    ) {
        ResponseResult<Customer> result = new ResponseResult<>();
        Customer customer = personService.selectCustomer(session);
        result.setData(customer);
        return result;
    }

    @RequestMapping("/updateCustomer")
    @ResponseBody
    public ResponseResult<Void> updateCustomer (
            HttpSession session,
            HttpServletRequest request,
            MultipartFile avatarFile,
            @RequestParam("nickname") String nickName,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email
    ) throws IOException {
        Customer customer = new Customer();
        customer.setCmNickname(nickName);
        customer.setCmPhone(phone);
        customer.setCmEmail(email);
        if (avatarFile.getSize() != 0) {
            String filePath = UploadFile.uploadFile(request, avatarFile);
            customer.setCmHeadPhoto(filePath);
        } else {
            String originImg = personService.selectCustomer(session).getCmHeadPhoto();
            customer.setCmHeadPhoto(originImg);
        }
        personService.updateCustomer(session, customer);
        return new ResponseResult<>();
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public ResponseResult<Void> updatePassword (
            HttpSession session,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword
    ) {
        personService.updatePassword(session, oldPassword, newPassword);
        return new ResponseResult<Void>();
    }


    @RequestMapping("/selectCertifyCustomer")
    @ResponseBody
    public ResponseResult<Customer> selectCertifyCustomer (
            HttpSession session
    ) {
        ResponseResult<Customer> result = new ResponseResult<>();
        Customer customer = personService.selectCertifyCustomer(session);
        result.setData(customer);
        return result;
    }
    @RequestMapping("/certifyCustomer")
    @ResponseBody
    public ResponseResult<Void> certifyCustomer (
            HttpServletRequest request,
            HttpSession session,
            MultipartFile cardPhoto,
            @RequestParam("name") String name,
            @RequestParam("cardID") String cardID
    ) throws IOException {
        Customer customer = new Customer();
        customer.setCmName(name);
        customer.setCmCardID(cardID);
        customer.setCmStatus(2);
        String filePath = new String();
        if (cardPhoto.getSize() != 0) {
            filePath = UploadFile.uploadFile(request, cardPhoto);
            System.out.println(filePath);
        } else {
            filePath = "/upload/list-head.png";
        }
        customer.setCmCardPhoto(filePath);
        personService.certifyCustomer(session, customer);
        return new ResponseResult<Void>();
    }

    @RequestMapping("/getCertifyStatus")
    @ResponseBody
    public ResponseResult<Integer> getCertifyStatus (HttpSession session) {
        ResponseResult<Integer> result = new ResponseResult<>();
        Integer data = personService.getCertifyStatus(session);
        result.setData(data);
        return result;
    }


    @RequestMapping("/getAllMessage")
    @ResponseBody
    public ResponseResult<List<Message>> getAllMessage () {
        ResponseResult<List<Message>> result = new ResponseResult<>();
        List<Message> list = personService.getAllMessage();
        result.setData(list);
        return result;
    }

    @RequestMapping("/getSingleMessage")
    @ResponseBody
    public ResponseResult<String> getSingleMessage (
        @RequestParam("id") Integer id
    ) {
        ResponseResult<String> result = new ResponseResult<>();
        String message = personService.getSingleMessage(id);
        result.setData(message);
        return result;
    }

    @RequestMapping("/deleteSingleMessage")
    @ResponseBody
    public ResponseResult<Void> deleteSingleMessage (
            @RequestParam("id") String id
    ) {
        Integer mid = Integer.parseInt(id);
        personService.deleteSingleMessage(mid);
        return new ResponseResult<>();
    }

}
