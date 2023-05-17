package com.letg.day_cut.model.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class PluginVO implements Serializable {
    /**
     * 是否订阅
     */
    private Integer subscribe;

    /**
     * 界面风格编号
     */
    private Integer styleType;

    private String styleTypeCode;
    /**
     * 界面风格描述
     */
    private String styleTypeDesc;

    /**
     * 字体编号
     */
    private int fontType;
    
    private String fontTypeCode;

    /**
     * 字体风格描述
     */

    private String fontTypeDesc;


    /**
     * 界面风格
     */
    public enum Style {
        STYLE_0("默认风格", "default", 0), STYLE_1("护眼风格", "protectEyes", 1),
        STYLE_2("其他风格", "other", 2);

        String desc;
        String name;
        int code;


        Style(String desc, String name, int code) {
            this.name = name;
            this.code = code;
            this.desc = desc;
        }

        public static String getNameByCode(int code) {
            Style[] styles = Style.values();
            for (Style style : styles) {
                if (style.getCode() == code) {
                    return style.getName();
                }
            }
            return null;
        }

        public static String getDescByCode(int code) {
            Style[] styles = Style.values();
            for (Style style : styles) {
                if (style.getCode() == code) {
                    return style.getDesc();
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 字体大小
     */
    public enum Font {
        Font_0("小","14px",0), Font_1("中","16px",1), Font_2("大","18px",2);;
        int code;
        String name;
        String desc;


        Font(String desc, String name, int code) {
            this.name = name;
            this.code = code;
            this.desc = desc;
        }

        public static String getNameByCode(int code) {
            Font[] fonts = Font.values();
            for (Font font : fonts) {
                if (font.getCode() == code) {
                    return font.getName();
                }
            }
            return null;
        }

        public static String getDescByCode(int code) {
            Font[] fonts = Font.values();
            for (Font font : fonts) {
                if (font.getCode() == code) {
                    return font.getDesc();
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PluginVO pluginVO = (PluginVO) o;
        return fontType == pluginVO.fontType && Objects.equals(subscribe, pluginVO.subscribe) && Objects.equals(styleType, pluginVO.styleType) && Objects.equals(styleTypeCode, pluginVO.styleTypeCode) && Objects.equals(styleTypeDesc, pluginVO.styleTypeDesc) && Objects.equals(fontTypeCode, pluginVO.fontTypeCode) && Objects.equals(fontTypeDesc, pluginVO.fontTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscribe, styleType, styleTypeCode, styleTypeDesc, fontType, fontTypeCode, fontTypeDesc);
    }
}

