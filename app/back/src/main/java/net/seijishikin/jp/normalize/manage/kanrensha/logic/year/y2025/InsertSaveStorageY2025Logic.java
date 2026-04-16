package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.SaveFileStorage2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.SaveFileStorage2025Repository;


/**
 * 書証保存挿入Logic(2025)
 */
@Component
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class InsertSaveStorageY2025Logic {

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    /** 書証保存Repository */
    @Autowired
    private SaveFileStorage2025Repository saveFileStorage2025Repository;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param userDto ユーザ最低限Dto
     * @param path    保存パス
     * @return 登録ID
     */
    public Integer practice(final LeastUserDto userDto, final Path path, final Short shoshoKbn) {

        Path pathParent = Paths.get(storageFolder);
        
        String childDir = "";
        if (path.toString().startsWith(pathParent.toString())) {
            String pathParentText = pathParent.toString();
            String pathText = path.getParent().toString();
            childDir = pathText.substring(pathParentText.length()+1, pathText.length());
            childDir = childDir.replaceAll("\\\\", "/");
        }
        
        SaveFileStorage2025Entity entityStorage = new SaveFileStorage2025Entity();
        setTableDataHistoryUtil.practiceInsert(userDto, entityStorage);

        entityStorage.setChildDir(childDir);
        entityStorage.setFileName(path.getFileName().toString());
        entityStorage.setIsLatest(true);
        entityStorage.setRegistTimeText(path.getParent().getParent().getFileName().toString());
        entityStorage.setShoshoKbn(shoshoKbn);

        Integer code = 1;
        Optional<SaveFileStorage2025Entity> optional = saveFileStorage2025Repository.findById(code);
        if (!optional.isEmpty()) {
            code += optional.get().getSaveFileStorageCode();
        }
        entityStorage.setSaveFileStorageCode(code);
        entityStorage.setSaveFileStorageId(0); // auto_increment明示

        return saveFileStorage2025Repository.save(entityStorage).getSaveFileStorageId();
    }

}
