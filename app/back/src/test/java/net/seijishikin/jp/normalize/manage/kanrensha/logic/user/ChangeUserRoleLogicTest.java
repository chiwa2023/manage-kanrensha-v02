package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ChangeUserRoleLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ChangeUserRoleLogicTest.sql")
class ChangeUserRoleLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ChangeUserRoleLogic changeUserRoleLogic;

    /** 登録済コード運営者 */
    private static final int managerCode = 12;
    /** 登録済コードAPIパートナー */
    private static final int partnerCode = 22;
    /** 登録済コード個人 */
    private static final String personCode = "49261-2mMW-rB-Pl4zrX";

    /** 空白文字 */
    private static final String BLANK = "";

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        /*
         * テストの前提
         * 
         * 権限全削除は退会処理を促しているので除外 関連者企業・団体、関連者政治団体は権限変更不可(利用者になりたい場合は別アカウント作成)
         */

        final LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        // 権限変更がない場合は、空リストを返して権限リストを更新しない(ユーザテーブルとはemailとしか紐づいていない)
        List<UserRoleEntity> listOld00 = new ArrayList<>();
        listOld00.add(this.createManager(true));
        List<String> listNew00 = new ArrayList<>();
        listNew00.add(UserRoleConstants.MANAGER);
        List<UserRoleEntity> listAns00 = changeUserRoleLogic.practice(listOld00, listNew00, userDto);
        assertTrue(listAns00.isEmpty());
        
        // 関連者企業・団体のみ、関連者政治団体のみは操作画面で変更を禁止しているので常に空リスト(テスト対象外)

        // 利用者単独の場合
        // 1.関連者個人(詳細登録を追加)を追加できる
        List<UserRoleEntity> listOld011 = new ArrayList<>();
        listOld011.add(this.createManager(true));
        List<String> listNew011 = new ArrayList<>();
        listNew011.add(UserRoleConstants.KANRENSHA_PERSON);
        listNew011.add(UserRoleConstants.MANAGER);
        List<UserRoleEntity> listAns011 = changeUserRoleLogic.practice(listOld011, listNew011, userDto);
        assertEquals(2, listAns011.size());
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, listAns011.get(0).getRole());
        String code010 = listAns011.get(0).getKanrenshaCode(); // 複写して詳細を残した
        assertNotEquals(BLANK, code010);
        assertNotEquals(personCode, code010); // 元コードでない
        assertEquals(UserRoleConstants.MANAGER, listAns011.get(1).getRole());
        assertEquals(managerCode, listAns011.get(1).getRiyoushaCode()); // 詳細が維持されている

        // 2.別利用者を追加できる
        List<UserRoleEntity> listOld012 = new ArrayList<>();
        listOld012.add(this.createManager(true));
        List<String> listNew012 = new ArrayList<>();
        listNew012.add(UserRoleConstants.PARTNER_API);
        listNew012.add(UserRoleConstants.MANAGER);
        List<UserRoleEntity> listAns012 = changeUserRoleLogic.practice(listOld012, listNew012, userDto);
        assertEquals(2, listAns012.size());
        assertEquals(UserRoleConstants.PARTNER_API, listAns012.get(0).getRole());
        assertEquals(0, listAns012.get(0).getRiyoushaCode()); // 詳細が登録しない
        assertEquals(UserRoleConstants.MANAGER, listAns012.get(1).getRole());
        assertEquals(managerCode, listAns012.get(1).getRiyoushaCode()); // 詳細が維持されている

        // 3.別利用者(詳細登録なし)と入れ替えできる
        List<UserRoleEntity> listOld013 = new ArrayList<>();
        listOld013.add(this.createManager(true));
        List<String> listNew013 = new ArrayList<>();
        listNew013.add(UserRoleConstants.PARTNER_API);
        List<UserRoleEntity> listAns013 = changeUserRoleLogic.practice(listOld013, listNew013, userDto);
        assertEquals(1, listAns013.size());
        assertEquals(UserRoleConstants.PARTNER_API, listAns013.get(0).getRole());
        Integer code013 = listAns013.get(0).getRiyoushaCode();// 複写して詳細を残した
        assertNotEquals(0, code013);
        assertNotEquals(partnerCode, code013);

        // 4.関連者個人(詳細登録を追加)を追加して利用者をなくす
        List<UserRoleEntity> listOld014 = new ArrayList<>();
        listOld014.add(this.createManager(true));
        List<String> listNew014 = new ArrayList<>();
        listNew014.add(UserRoleConstants.KANRENSHA_PERSON);
        List<UserRoleEntity> listAns014 = changeUserRoleLogic.practice(listOld014, listNew014, userDto);
        assertEquals(1, listAns014.size());
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, listAns014.get(0).getRole());
        String code014 = listAns014.get(0).getKanrenshaCode(); // 複写して詳細を残した
        assertNotEquals(BLANK, code014);
        assertNotEquals(personCode, code014); // 元コードでない

        // 利用者複数の場合(省略)
        // 1.関連者個人(詳細登録を追加)を追加できる
        // 2.利用者を削除できる(詳細登録なし)
        // 3.利用者を削除できる(詳細登録あり)
        // 4.利用者すべてをなくして関連者個人(詳細登録を追加)を追加できる

        // 関連者個人のみ
        // 関連者をなしにする→退会扱い(操作画面段階で禁止)なのでみない
        // 1.利用者(詳細登録はする)を1追加できる
        List<UserRoleEntity> listOld031 = new ArrayList<>();
        listOld031.add(this.createPerson(true));
        List<String> listNew031 = new ArrayList<>();
        listNew031.add(UserRoleConstants.KANRENSHA_PERSON);
        listNew031.add(UserRoleConstants.MANAGER);
        List<UserRoleEntity> listAns031 = changeUserRoleLogic.practice(listOld031, listNew031, userDto);
        assertEquals(2, listAns031.size());
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, listAns031.get(0).getRole());
        assertEquals(personCode, listAns031.get(0).getKanrenshaCode()); // 詳細が維持されている
        assertEquals(UserRoleConstants.MANAGER, listAns031.get(1).getRole());
        Integer code031 = listAns031.get(1).getRiyoushaCode(); // 複写して詳細を残した
        assertNotEquals(0, code031);
        assertNotEquals(managerCode, code031);

        // 2.利用者を2(どちらかを詳細登録し、片方はしない)追加できる
        List<UserRoleEntity> listOld032 = new ArrayList<>();
        listOld032.add(this.createPerson(true));
        List<String> listNew032 = new ArrayList<>();
        listNew032.add(UserRoleConstants.KANRENSHA_PERSON);
        listNew032.add(UserRoleConstants.MANAGER);
        listNew032.add(UserRoleConstants.PARTNER_API);
        List<UserRoleEntity> listAns032 = changeUserRoleLogic.practice(listOld032, listNew032, userDto);
        assertEquals(3, listAns032.size());
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, listAns032.get(0).getRole());
        assertEquals(personCode, listAns032.get(0).getKanrenshaCode());
        assertEquals(UserRoleConstants.MANAGER, listAns032.get(1).getRole());
        Integer code032 = listAns032.get(1).getRiyoushaCode(); // 複写して詳細を残した
        assertNotEquals(0, code032);
        assertNotEquals(managerCode, code032);
        assertEquals(UserRoleConstants.PARTNER_API, listAns032.get(2).getRole());
        assertEquals(0, listAns032.get(2).getRiyoushaCode()); // 後から処理される側は詳細登録なし(0)となる

        // 3.利用者を1詳細登録はする)追加したうえで関連者をなしにする
        List<UserRoleEntity> listOld033 = new ArrayList<>();
        listOld033.add(this.createPerson(true));
        List<String> listNew033 = new ArrayList<>();
        listNew033.add(UserRoleConstants.MANAGER);
        List<UserRoleEntity> listAns033 = changeUserRoleLogic.practice(listOld033, listNew033, userDto);
        assertEquals(1, listAns033.size());
        assertEquals(UserRoleConstants.MANAGER, listAns033.get(0).getRole());
        Integer code033 = listAns033.get(0).getRiyoushaCode(); // 複写して詳細を残した
        assertNotEquals(0, code033);
        assertNotEquals(managerCode, code033);

        // 4.利用者を2(どちらかを詳細登録し、片方はしない)追加したうえで関連者をなしにする
        List<UserRoleEntity> listOld034 = new ArrayList<>();
        listOld034.add(this.createPerson(true));
        List<String> listNew034 = new ArrayList<>();
        listNew034.add(UserRoleConstants.MANAGER);
        listNew034.add(UserRoleConstants.PARTNER_API);
        List<UserRoleEntity> listAns034 = changeUserRoleLogic.practice(listOld034, listNew034, userDto);
        assertEquals(2, listAns034.size());
        assertEquals(UserRoleConstants.MANAGER, listAns034.get(0).getRole());
        Integer code034 = listAns034.get(0).getRiyoushaCode(); // 複写して詳細を残した
        assertNotEquals(0, code034);
        assertNotEquals(managerCode, code034);
        assertEquals(UserRoleConstants.PARTNER_API, listAns034.get(1).getRole());
        assertEquals(0, listAns034.get(1).getRiyoushaCode());

        // 利用者と関連者個人の場合
        // 利用者と関連者個人両方削除は退会扱い(操作画面段階で禁止)なのでみない
        // 1.利用者を残して関連者個人を削除できる
        List<UserRoleEntity> listOld041 = new ArrayList<>();
        listOld041.add(this.createPartnerApi(true));
        listOld041.add(this.createPerson(true));
        List<String> listNew041 = new ArrayList<>();
        listNew041.add(UserRoleConstants.PARTNER_API);
        List<UserRoleEntity> listAns041 = changeUserRoleLogic.practice(listOld041, listNew041, userDto);
        assertEquals(1, listAns041.size());
        UserRoleEntity roleEntity0410 = listAns041.get(0);
        assertEquals(UserRoleConstants.PARTNER_API, roleEntity0410.getRole());
        assertEquals(partnerCode, roleEntity0410.getRiyoushaCode()); // 詳細が維持されている

        // 2.関連者個人をのこして利用者を削除できる
        List<UserRoleEntity> listOld042 = new ArrayList<>();
        listOld042.add(this.createPartnerApi(true));
        listOld042.add(this.createPerson(true));
        List<String> listNew042 = new ArrayList<>();
        listNew042.add(UserRoleConstants.KANRENSHA_PERSON);
        List<UserRoleEntity> listAns042 = changeUserRoleLogic.practice(listOld042, listNew042, userDto);
        assertEquals(1, listAns042.size());
        UserRoleEntity roleEntity0420 = listAns042.get(0);
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, roleEntity0420.getRole());
        assertEquals(personCode, roleEntity0420.getKanrenshaCode()); // 詳細が維持されている

        // 3.利用者(詳細あり)を利用者(詳細なし)に入れ替え、関連者個人を削除できる
        List<UserRoleEntity> listOld043 = new ArrayList<>();
        listOld043.add(this.createPartnerApi(true));
        listOld043.add(this.createPerson(true));
        List<String> listNew043 = new ArrayList<>();
        listNew043.add(UserRoleConstants.MANAGER);
        List<UserRoleEntity> listAns043 = changeUserRoleLogic.practice(listOld043, listNew043, userDto);
        assertEquals(1, listAns043.size());
        UserRoleEntity roleEntity0430 = listAns043.get(0);
        assertEquals(UserRoleConstants.MANAGER, roleEntity0430.getRole());
        Integer code043 = listAns043.get(0).getRiyoushaCode(); // 複写して詳細を残した
        assertNotEquals(0, code043);
        assertNotEquals(managerCode, code043);

        // 利用者2と関連者個人の場合
        // 1.利用者(詳細登録あり)を残して関連者個人を削除できる
        List<UserRoleEntity> listOld051 = new ArrayList<>();
        listOld051.add(this.createManager(true));
        listOld051.add(this.createPartnerApi(false));
        listOld051.add(this.createPerson(true));
        List<String> listNew051 = new ArrayList<>();
        listNew051.add(UserRoleConstants.MANAGER);
        List<UserRoleEntity> listAns051 = changeUserRoleLogic.practice(listOld051, listNew051, userDto);
        assertEquals(1, listAns051.size());
        UserRoleEntity roleEntity0510 = listAns051.get(0);
        assertEquals(UserRoleConstants.MANAGER, roleEntity0510.getRole());
        assertEquals(managerCode, roleEntity0510.getRiyoushaCode()); // 詳細が維持されている

        // 2.利用者(詳細登録なし)を残して関連者個人を削除できる
        List<UserRoleEntity> listOld052 = new ArrayList<>();
        listOld052.add(this.createManager(true));
        listOld052.add(this.createPartnerApi(false));
        listOld052.add(this.createPerson(true));
        List<String> listNew052 = new ArrayList<>();
        listNew052.add(UserRoleConstants.PARTNER_API);
        List<UserRoleEntity> listAns052 = changeUserRoleLogic.practice(listOld052, listNew052, userDto);
        assertEquals(1, listAns052.size());
        UserRoleEntity roleEntity0520 = listAns052.get(0);
        assertEquals(UserRoleConstants.PARTNER_API, roleEntity0520.getRole());
        Integer code052 = listAns052.get(0).getRiyoushaCode();// 複写して詳細を残した
        assertNotEquals(0, code052);
        assertNotEquals(partnerCode, code052);

        // 3.関連者個人をのこして利用者2を削除できる
        List<UserRoleEntity> listOld053 = new ArrayList<>();
        listOld053.add(this.createManager(true));
        listOld053.add(this.createPartnerApi(false));
        listOld053.add(this.createPerson(true));
        List<String> listNew053 = new ArrayList<>();
        listNew053.add(UserRoleConstants.KANRENSHA_PERSON);
        List<UserRoleEntity> listAns053 = changeUserRoleLogic.practice(listOld053, listNew053, userDto);
        assertEquals(1, listAns053.size());
        UserRoleEntity roleEntity0530 = listAns053.get(0);
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, roleEntity0530.getRole());
        assertEquals(personCode, roleEntity0530.getKanrenshaCode()); // 詳細が維持されている

        // 4.関連者個人をのこして利用者1(詳細なし)を削除できる
        List<UserRoleEntity> listOld054 = new ArrayList<>();
        listOld054.add(this.createManager(true));
        listOld054.add(this.createPartnerApi(false));
        listOld054.add(this.createPerson(true));
        List<String> listNew054 = new ArrayList<>();
        listNew054.add(UserRoleConstants.MANAGER);
        listNew054.add(UserRoleConstants.KANRENSHA_PERSON);
        List<UserRoleEntity> listAns054 = changeUserRoleLogic.practice(listOld054, listNew054, userDto);
        assertEquals(2, listAns054.size());
        UserRoleEntity roleEntity0540 = listAns054.get(0);
        assertEquals(UserRoleConstants.MANAGER, roleEntity0540.getRole());
        assertEquals(managerCode, roleEntity0540.getRiyoushaCode()); // 詳細が維持されている
        UserRoleEntity roleEntity0541 = listAns054.get(1);
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, roleEntity0541.getRole());
        assertEquals(personCode, roleEntity0541.getKanrenshaCode()); // 詳細が維持されている

        // 5.関連者個人をのこして利用者1(詳細あり)を削除できる
        List<UserRoleEntity> listOld055 = new ArrayList<>();
        listOld055.add(this.createManager(true));
        listOld055.add(this.createPartnerApi(false));
        listOld055.add(this.createPerson(true));
        List<String> listNew055 = new ArrayList<>();
        listNew055.add(UserRoleConstants.PARTNER_API);
        listNew055.add(UserRoleConstants.KANRENSHA_PERSON);
        List<UserRoleEntity> listAns055 = changeUserRoleLogic.practice(listOld055, listNew055, userDto);
        assertEquals(2, listAns055.size());
        UserRoleEntity roleEntity0550 = listAns055.get(0);
        assertEquals(UserRoleConstants.PARTNER_API, roleEntity0550.getRole());
        Integer code055 = listAns055.get(0).getRiyoushaCode();// 複写して詳細を残した
        assertNotEquals(0, code055);
        assertNotEquals(partnerCode, code055);
        UserRoleEntity roleEntity0551 = listAns055.get(1);
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, roleEntity0551.getRole());
        assertEquals(personCode, roleEntity0551.getKanrenshaCode()); // 詳細が維持されている

        // SE権限維持
        List<UserRoleEntity> listOld060 = new ArrayList<>();
        listOld060.add(this.createManager(true));
        listOld060.add(this.createPartnerApi(false));
        listOld060.add(this.createAdmin());
        List<String> listNew060 = new ArrayList<>();
        listNew060.add(UserRoleConstants.MANAGER);
        List<UserRoleEntity> listAns060 = changeUserRoleLogic.practice(listOld060, listNew060, userDto);
        assertEquals(2, listAns060.size());
        UserRoleEntity roleEntity0600 = listAns060.get(0);
        assertEquals(UserRoleConstants.MANAGER, roleEntity0600.getRole());
        assertEquals(managerCode, roleEntity0600.getRiyoushaCode()); // 詳細が維持されている
        UserRoleEntity roleEntity0601 = listAns060.get(1);
        assertEquals(UserRoleConstants.ADMIN, roleEntity0601.getRole());
        assertEquals(0, roleEntity0601.getRiyoushaCode()); // 登録がないまま

        // SE権限喪失
        List<UserRoleEntity> listOld070 = new ArrayList<>();
        listOld070.add(this.createManager(true));
        listOld070.add(this.createPartnerApi(false));
        listOld070.add(this.createAdmin());
        List<String> listNew070 = new ArrayList<>();
        listNew070.add(UserRoleConstants.PARTNER_API);
        List<UserRoleEntity> listAns070 = changeUserRoleLogic.practice(listOld070, listNew070, userDto);
        assertEquals(1, listAns070.size());
        UserRoleEntity roleEntity0700 = listAns070.get(0);
        assertEquals(UserRoleConstants.PARTNER_API, roleEntity0700.getRole());
        Integer code070 = listAns070.get(0).getRiyoushaCode();// 複写して詳細を残した
        assertNotEquals(0, code070);
        assertNotEquals(partnerCode, code070);
    }

    private UserRoleEntity createManager(final boolean hasDetail) {

        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRole(UserRoleConstants.MANAGER);

        if (hasDetail) {
            roleEntity.setRiyoushaCode(managerCode);
        }

        return roleEntity;
    }

    private UserRoleEntity createPartnerApi(final boolean hasDetail) {

        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRole(UserRoleConstants.PARTNER_API);

        if (hasDetail) {
            roleEntity.setRiyoushaCode(partnerCode);
        }

        return roleEntity;
    }

    private UserRoleEntity createPerson(final boolean hasDetail) {

        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRole(UserRoleConstants.KANRENSHA_PERSON);

        if (hasDetail) {
            roleEntity.setKanrenshaCode(personCode);
        }

        return roleEntity;
    }

    private UserRoleEntity createAdmin() {

        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRole(UserRoleConstants.ADMIN);

        return roleEntity;
    }

}
