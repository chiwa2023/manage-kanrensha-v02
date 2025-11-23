package net.seijishikin.jp.normalize.manage.kanrensha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 全体起動
 */
@SpringBootApplication
@EnableAsync
public class BackApplication { // NOPMD UtilityClass

    /**
     * 起動メソッド
     *
     * @param args 引数
     */
    public static void main(final String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

}
