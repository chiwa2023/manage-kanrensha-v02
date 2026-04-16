package net.seijishikin.jp.normalize.manage.kanrensha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 全体起動
 */
@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = { "net.seijishikin.jp.normalize.manage.kanrensha",
        "net.seijishikin.jp.normalize.common_tool" })
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
