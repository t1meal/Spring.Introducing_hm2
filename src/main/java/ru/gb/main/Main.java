package ru.gb.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.main.config.AppConfig;
import ru.gb.main.utils.Cart;
import ru.gb.main.utils.CartFactory;
import ru.gb.main.utils.Product;

import java.util.Scanner;

public class Main {

    private static Cart cart;
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        mainCommands(context);
        System.out.println(context);
    }

    public static void mainCommands(ApplicationContext context){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Режим взаимодействия с корзиной");
        while (true) {
            String str = scanner1.nextLine();
            if (str.equals("/getCart")) {
                CartFactory cartFactory = context.getBean(CartFactory.class);
                Cart newCart = cartFactory.getCart();
                setCart(newCart);
                System.out.println("Новая корзина создана!");
            }
            if (str.equals("/exist")) {
                if (cart == null){
                    System.out.println("Корзина не создана!");
                } else {
                    System.out.println("Корзина существует! Доступны операции с корзиной.");
                }
            }
            if (str.equals("/content")){
                System.out.println("В корзине находится " + cart.getCartProductList().size() + " продуктов");
                for (Product e: cart.getCartProductList()) {
                    System.out.println(e.getProductToString());
                }
            }
            if (str.equals("/add")){
                addCommands(context);
                scanner1.close();
            }
            if (str.equals("/remove")){
                removeCommands(context);
            }
            if (str.equals("/info")){
                System.out.println("Доступные команды:");
                System.out.println(". /getCart");
                System.out.println(". /exist");
                System.out.println(". /content");
                System.out.println(". /add");
                System.out.println(". /remove//");
            }
        }
    }
    public static void addCommands(ApplicationContext context){
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Введите номер продукта который вы хотите добавить (от 1 до 5)");
        System.out.println("Для выхода из режима нажмите 0");
        while (true){
            int i = scanner2.nextInt();
            if (i >= 1 && i < 6){
                cart.addProductForIndex(i);
                System.out.println("Продукт с индексом " + i + " добавлен в корзину!");
            } else if (i == 0){
                mainCommands(context);
                scanner2.close();
            } else {
                System.out.println("Введено не правильно число!");
            }
        }
    }
    public static void removeCommands(ApplicationContext context){
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Введите номер продукта который вы хотите удалить (от 1 до 5)");
        System.out.println("Для выхода из режима нажмите 0");
        while (true){
            int i = scanner3.nextInt();
            if (i >= 1 && i < 6){
                cart.removeProductForIndex(i);
                System.out.println("Продукт с индексом " + i + " удален из корзины!");
            } else if (i == 0){
                mainCommands(context);
                scanner3.close();
            } else {
                System.out.println("Введено не правильно число!");
            }
        }
    }
    public static void setCart(Cart cart) {
        Main.cart = cart;
    }
}
