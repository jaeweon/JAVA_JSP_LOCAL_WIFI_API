package com;
import java.util.Scanner;
public class dbTestMain {
    public static void main(String[] args) {
        MemberService MemberService = new MemberService();
//        dbTest.dbInsert();
//        dbTest.dbUpdate();
//        dbTest.dbDelete();
        Scanner sc = new Scanner(System.in);
        String memberType = "email";

        System.out.println("아이디를 입력 해주세요 : ");
        String userId = sc.next();

        System.out.println("비밀번호를 입력 해주세요 : ");
        String pw = sc.next();

        System.out.println("이름을 입력 해주세요 : ");
        String name = sc.next();

        Member member = new Member();
        member.setMemberType(memberType);
        member.setUserId(userId);
        member.setPw(pw);
        member.setName(name);
        MemberService.register(member);


        NearWifiService NearWifiService = new NearWifiService();
        NearWifi near = new NearWifi();
        String wifiName = near.getWifiName();
        near.setWifiName(wifiName);
        NearWifiService.wifiSelect(near);

//        MemberService.withDraw(member);

    }
}