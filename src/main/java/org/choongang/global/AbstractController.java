package org.choongang.global;

import org.choongang.global.constants.Menu;
import org.choongang.main.MainRouter;
import org.choongang.main.controllers.MainController;

import java.util.Scanner;

public abstract class AbstractController implements Controller {
    protected Scanner sc;

    public AbstractController(){
        sc = new Scanner(System.in);
    }
    /**
     * 상단 공통 출력 부분
     */
    public void common(){
        System.out.println("묵찌빠 프로그램");
        System.out.println("------------------------");
    }

    /**
     * 입력항목
     * - 문자 : q, exit, quit (종료)
     * - 숫자 : 메뉴항목
     */
    public void prompt(){
       System.out.print("메뉴 선택: ");
       String menu = sc.nextLine();
       if(menu.equals("q") || menu.equals("quit") || menu.equals("exit")){
           System.exit(0); //0 - 정상종료, 1 - 비정상 종료
       }
       try{
           int m = Integer.parseInt(menu);
           change(m); //메뉴변경
       }catch(Exception e){
           System.out.println("메뉴는 숫자로 입력하세요.");
       }
    }


    /**
     * 템플릿 메서드 패턴 : 특정 절차가 고정되어 있는 경우
     * 고정되어 있기 때문에 final
     */
    @Override
    public final void run(){
        common();
        show();
        prompt();
    }

    private void change(int menuNo){
        Menu menu = null;
        switch (menuNo){
            case 1: menu = Menu.JOIN; break; //회원가입
            case 2: menu = Menu.LOGIN; break; //로그인
            default: menu = Menu.MAIN;  //메인메뉴
        }

        // 메뉴 컨트롤러 변경 처리 - Router
        MainRouter.getInstance().change(menu);

    }
}
