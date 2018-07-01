package com.bnrc.bnrcbus.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by apple on 2018/5/24.
 */

public enum IconFonts implements Icon {
    icon_home('\ue642'),
    icon_route('\ue61b'),
    icon_ar('\ue8f0'),
    icon_buscircle('\ue67e'),
    icon_message('\ue625'),
    icon_menu('\ue652'),
    icon_search('\ue63c'),
    icon_service('\ue639'),
    icon_railway('\ue6e6'),
    icon_setting('\ue6e8'),
    icon_about('\ue619'),
    icon_feedback('\ue660'),
    icon_share('\ue60a'),
    icon_modify('\ue612'),
    icon_map('\ue613'),
    icon_qq('\ue667'),
    icon_wechat('\ue63a'),
    icon_sina('\ue6ad'),
    icon_back('\ue636'),
    icon_loc('\ue655'),
    icon_bus('\ue609'),
    icon_final('\ue607'),
    icon_rightarrow('\ue60d'),
    icon_downarrow('\ue623');


    private char character;

    IconFonts(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}