package net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * Webフルパス変換Logic
 */
@Component
public class ConvertFullWebPathLogic {

    /** ドメイン(frontend)URL */
    @Value("${app.frontend.base-url:http://localhost:5173}")
    private List<String> allowedOrigins;

    /**
     * 下位パスをフルパスに変更する
     * 
     * @param path 下位パス
     * @return フルパス
     */
    public String practice(final String path) {

        return allowedOrigins.get(0) + PathRouteConstants.ROOT + path;
    }

}
