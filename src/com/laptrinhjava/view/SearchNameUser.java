package com.laptrinhjava.view;

import com.laptrinhjava.controller.UserController;
import com.laptrinhjava.dto.response.UserResponse;

import java.util.List;
import java.util.Scanner;

public class SearchNameUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id cần tìm : ");
        Integer id = scanner.nextInt();
        UserController userController = new UserController();
        List<UserResponse> userResponses = userController.userSearchById(id);
        for (UserResponse userResponse : userResponses) {
            System.out.println("Name : " + userResponse.getFullname());
        }
    }
}