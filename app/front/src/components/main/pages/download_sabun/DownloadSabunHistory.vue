<script setup lang="ts">
const now: Date = new Date();
// 差分作成日時を取得する
const startDate: Date = new Date(now.getFullYear(), 1, 1);
const endDate: Date = new Date(now.getFullYear(), 3, 31);

function onCancel() {
    history.back();
}
</script>
<template>
    <!-- サイト利用登録者限定ページではないのでユーザチェックがない -->
    <h1>関連者履歴データダウンロード(差分)</h1>

    <div class="one-line">
        {{ startDate.toLocaleDateString() }} から {{ endDate.toLocaleDateString() }}までを収録
    </div>

    <h3>関連者一括ダウンロード</h3>
    <div class="one-line">
        <a href="dump_history_sabun/sabun_partner_history_all.zip">一括ダウンロード</a>
    </div>

    <h3>関連者個人</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <td>一括</td>
                    <td><a href="/dump/dump_history_sabun_person.zip">一括</a></td>
                    <td colspan="12">&nbsp;</td>
                </tr>
                <tr>
                    <td>01:北海道</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_01.csv">01:北海道</a></td>
                    <td>02:青森県</td>
                    <td><a href="/dump/dump_history_sabun_person/abun_partner_person_history_02.csv">02:青森県</a></td>
                    <td>03:岩手県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_03.csv">03:岩手県</a></td>
                    <td>04:宮城県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_04.csv">04:宮城県</a></td>
                    <td>05:秋田県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_05.csv">05:秋田県</a></td>
                    <td>06:山形県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_06.csv">06:山形県</a></td>
                    <td>07:福島県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_07.csv">07:福島県</a></td>
                </tr>
                <tr>
                    <td>08:茨城県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_08.csv">08:茨城県</a></td>
                    <td>09:栃木県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_09.csv">09:栃木県</a></td>
                    <td>10:群馬県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_10.csv">10:群馬県</a></td>
                    <td>11:埼玉県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_11.csv">11:埼玉県</a></td>
                    <td>12:千葉県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_12.csv">12:千葉県</a></td>
                    <td>13:東京都</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_13.csv">13:東京都</a></td>
                    <td>14:神奈川県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_14.csv">14:神奈川県</a></td>
                </tr>
                <tr>
                    <td>15:新潟県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_15.csv">15:新潟県</a></td>
                    <td>16:富山県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_16.csv">16:富山県</a></td>
                    <td>17:石川県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_17.csv">17:石川県</a></td>
                    <td>18:福井県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_18.csv">18:福井県</a></td>
                    <td>19:山梨県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_19.csv">19:山梨県</a></td>
                    <td>20:長野県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_20.csv">20:長野県</a></td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td>21:岐阜県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_21.csv">21:岐阜県</a></td>
                    <td>22:静岡県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_22.csv">22:静岡県</a></td>
                    <td>23:愛知県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_23.csv">23:愛知県</a></td>
                    <td>24:三重県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_24.csv">24:三重県</a></td>
                    <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                    <td>25:滋賀県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_25.csv">25:滋賀県</a></td>
                    <td>26:京都府</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_26.csv">26:京都府</a></td>
                    <td>27:大阪府</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_27.csv">27:大阪府</a></td>
                    <td>28:兵庫県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_28.csv">28:兵庫県</a></td>
                    <td>29:奈良県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_29.csv">29:奈良県</a></td>
                    <td>30:和歌山県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_30.csv">30:和歌山県</a></td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td>31:鳥取県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_31.csv">31:鳥取県</a></td>
                    <td>32:島根県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_32.csv">32:島根県</a></td>
                    <td>33:岡山県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_33.csv">33:岡山県</a></td>
                    <td>34:広島県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_34.csv">34:広島県</a></td>
                    <td>35:山口県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_35.csv">35:山口県</a></td>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td>36:徳島県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_36.csv">36:徳島県</a></td>
                    <td>37:香川県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_37.csv">37:香川県</a></td>
                    <td>38:愛媛県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_38.csv">38:愛媛県</a></td>
                    <td>39:高知県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_39.csv">39:高知県</a></td>
                    <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                    <td>40:福岡県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_40.csv">40:福岡県</a></td>
                    <td>41:佐賀県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_41.csv">41:佐賀県</a></td>
                    <td>42:長崎県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_42.csv">42:長崎県</a></td>
                    <td>43:熊本県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_43.csv">43:熊本県</a></td>
                    <td>44:大分県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_44.csv">44:大分県</a></td>
                    <td>45:宮崎県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_45.csv">45:宮崎県</a></td>
                    <td>46:鹿児島県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_46.csv">46:鹿児島県</a></td>
                </tr>
                <tr>
                    <td>47:沖縄県</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_47.csv">47:沖縄県</a></td>
                    <td colspan="12">&nbsp;</td>
                </tr>
                <tr>
                    <td>99:その他</td>
                    <td><a href="/dump/dump_history_sabun_person/sabun_partner_person_history_99.csv">99:その他</a></td>
                    <td colspan="12">&nbsp;</td>
                </tr>
            </tbody>
        </table>
    </div>

    <h3>関連者企業／団体</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <td>一括</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt.zip">一括</a></td>
                    <td colspan="12">&nbsp;</td>
                </tr>
                <tr>
                    <td>01:北海道</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_01.csv">01:北海道</a></td>
                    <td>02:青森県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_02.csv">02:青森県</a></td>
                    <td>03:岩手県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_03.csv">03:岩手県</a></td>
                    <td>04:宮城県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_04.csv">04:宮城県</a></td>
                    <td>05:秋田県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_05.csv">05:秋田県</a></td>
                    <td>06:山形県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_06.csv">06:山形県</a></td>
                    <td>07:福島県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_07.csv">07:福島県</a></td>
                </tr>
                <tr>
                    <td>08:茨城県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_08.csv">08:茨城県</a></td>
                    <td>09:栃木県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_09.csv">09:栃木県</a></td>
                    <td>10:群馬県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_10.csv">10:群馬県</a></td>
                    <td>11:埼玉県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_11.csv">11:埼玉県</a></td>
                    <td>12:千葉県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_12.csv">12:千葉県</a></td>
                    <td>13:東京都</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_13.csv">13:東京都</a></td>
                    <td>14:神奈川県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_14.csv">14:神奈川県</a></td>
                </tr>
                <tr>
                    <td>15:新潟県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_15.csv">15:新潟県</a></td>
                    <td>16:富山県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_16.csv">16:富山県</a></td>
                    <td>17:石川県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_17.csv">17:石川県</a></td>
                    <td>18:福井県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_18.csv">18:福井県</a></td>
                    <td>19:山梨県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_19.csv">19:山梨県</a></td>
                    <td>20:長野県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_20.csv">20:長野県</a></td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td>21:岐阜県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_21.csv">21:岐阜県</a></td>
                    <td>22:静岡県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_22.csv">22:静岡県</a></td>
                    <td>23:愛知県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_23.csv">23:愛知県</a></td>
                    <td>24:三重県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_24.csv">24:三重県</a></td>
                    <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                    <td>25:滋賀県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_25.csv">25:滋賀県</a></td>
                    <td>26:京都府</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_26.csv">26:京都府</a></td>
                    <td>27:大阪府</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_27.csv">27:大阪府</a></td>
                    <td>28:兵庫県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_28.csv">28:兵庫県</a></td>
                    <td>29:奈良県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_29.csv">29:奈良県</a></td>
                    <td>30:和歌山県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_30.csv">30:和歌山県</a></td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td>31:鳥取県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_31.csv">31:鳥取県</a></td>
                    <td>32:島根県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_32.csv">32:島根県</a></td>
                    <td>33:岡山県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_33.csv">33:岡山県</a></td>
                    <td>34:広島県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_34.csv">34:広島県</a></td>
                    <td>35:山口県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_35.csv">35:山口県</a></td>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td>36:徳島県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_36.csv">36:徳島県</a></td>
                    <td>37:香川県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_37.csv">37:香川県</a></td>
                    <td>38:愛媛県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_38.csv">38:愛媛県</a></td>
                    <td>39:高知県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_39.csv">39:高知県</a></td>
                    <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                    <td>40:福岡県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_40.csv">40:福岡県</a></td>
                    <td>41:佐賀県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_41.csv">41:佐賀県</a></td>
                    <td>42:長崎県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_42.csv">42:長崎県</a></td>
                    <td>43:熊本県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_43.csv">43:熊本県</a></td>
                    <td>44:大分県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_44.csv">44:大分県</a></td>
                    <td>45:宮崎県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_45.csv">45:宮崎県</a></td>
                    <td>46:鹿児島県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_46.csv">46:鹿児島県</a></td>
                </tr>
                <tr>
                    <td>47:沖縄県</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_47.csv">47:沖縄県</a></td>
                    <td colspan="12">&nbsp;</td>
                </tr>
                <tr>
                    <td>99:その他</td>
                    <td><a href="/dump/dump_history_sabun_kigyou_dt/sabun_partner_kigyou_dt_history_99.csv">99:その他</a></td>
                    <td colspan="12">&nbsp;</td>
                </tr>
            </tbody>
        </table>
    </div>

    <h3>関連者政治団体</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <td>一括</td>
                    <td><a href="dump/dump_history_sabun_poli_org.zip">一括</a></td>
                    <td colspan="12">&nbsp;</td>
                </tr>
                <tr>
                    <td>01:北海道</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_01.csv">01:北海道</a>
                    </td>
                    <td>02:青森県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_02.csv">02:青森県</a>
                    </td>
                    <td>03:岩手県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_03.csv">03:岩手県</a>
                    </td>
                    <td>04:宮城県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_04.csv">04:宮城県</a>
                    </td>
                    <td>05:秋田県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_05.csv">05:秋田県</a>
                    </td>
                    <td>06:山形県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_06.csv">06:山形県</a>
                    </td>
                    <td>07:福島県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_07.csv">07:福島県</a>
                    </td>
                </tr>
                <tr>
                    <td>08:茨城県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_08.csv">08:茨城県</a>
                    </td>
                    <td>09:栃木県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_09.csv">09:栃木県</a>
                    </td>
                    <td>10:群馬県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_10.csv">10:群馬県</a>
                    </td>
                    <td>11:埼玉県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_11.csv">11:埼玉県</a>
                    </td>
                    <td>12:千葉県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_12.csv">12:千葉県</a>
                    </td>
                    <td>13:東京都</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_13.csv">13:東京都</a>
                    </td>
                    <td>14:神奈川県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_14.csv">14:神奈川県</a>
                    </td>
                </tr>
                <tr>
                    <td>15:新潟県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_15.csv">15:新潟県</a>
                    </td>
                    <td>16:富山県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_16.csv">16:富山県</a>
                    </td>
                    <td>17:石川県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_17.csv">17:石川県</a>
                    </td>
                    <td>18:福井県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_18.csv">18:福井県</a>
                    </td>
                    <td>19:山梨県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_19.csv">19:山梨県</a>
                    </td>
                    <td>20:長野県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_20.csv">20:長野県</a>
                    </td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td>21:岐阜県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_21.csv">21:岐阜県</a>
                    </td>
                    <td>22:静岡県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_22.csv">22:静岡県</a>
                    </td>
                    <td>23:愛知県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_23.csv">23:愛知県</a>
                    </td>
                    <td>24:三重県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_24.csv">24:三重県</a>
                    </td>
                    <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                    <td>25:滋賀県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_25.csv">25:滋賀県</a>
                    </td>
                    <td>26:京都府</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_26.csv">26:京都府</a>
                    </td>
                    <td>27:大阪府</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_27.csv">27:大阪府</a>
                    </td>
                    <td>28:兵庫県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_28.csv">28:兵庫県</a>
                    </td>
                    <td>29:奈良県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_29.csv">29:奈良県</a>
                    </td>
                    <td>30:和歌山県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_30.csv">30:和歌山県</a>
                    </td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td>31:鳥取県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_31.csv">31:鳥取県</a>
                    </td>
                    <td>32:島根県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_32.csv">32:島根県</a>
                    </td>
                    <td>33:岡山県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_33.csv">33:岡山県</a>
                    </td>
                    <td>34:広島県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_34.csv">34:広島県</a>
                    </td>
                    <td>35:山口県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_35.csv">35:山口県</a>
                    </td>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td>36:徳島県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_36.csv">36:徳島県</a>
                    </td>
                    <td>37:香川県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_37.csv">37:香川県</a>
                    </td>
                    <td>38:愛媛県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_38.csv">38:愛媛県</a>
                    </td>
                    <td>39:高知県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_39.csv">39:高知県</a>
                    </td>
                    <td colspan="6">&nbsp;</td>
                </tr>
                <tr>
                    <td>40:福岡県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_40.csv">40:福岡県</a>
                    </td>
                    <td>41:佐賀県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_41.csv">41:佐賀県</a>
                    </td>
                    <td>42:長崎県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_42.csv">42:長崎県</a>
                    </td>
                    <td>43:熊本県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_43.csv">43:熊本県</a>
                    </td>
                    <td>44:大分県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_44.csv">44:大分県</a>
                    </td>
                    <td>45:宮崎県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_45.csv">45:宮崎県</a>
                    </td>
                    <td>46:鹿児島県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_46.csv">46:鹿児島県</a>
                    </td>
                </tr>
                <tr>
                    <td>47:沖縄県</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_47.csv">47:沖縄県</a>
                    </td>
                    <td colspan="12">&nbsp;</td>
                </tr>
                <tr>
                    <td>99:その他</td>
                    <td><a href="/dump/dump_history_sabun_poli_org/sabun_partner_poli_org_history_99.csv">99:その他</a>
                    </td>
                    <td colspan="12">&nbsp;</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
