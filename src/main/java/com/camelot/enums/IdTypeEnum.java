package com.camelot.enums;

import com.baomidou.mybatisplus.annotation.IdType;

/**
 * @author Lord Camelot
 * @date 2024/2/20
 * @description
 */
public enum IdTypeEnum {
    AUTO("auto", IdType.AUTO),
    NONE("none", IdType.NONE),
    INPUT("input", IdType.INPUT),
    ASSIGN_ID("id", IdType.ASSIGN_ID),
    ASSIGN_UUID("uuid", IdType.ASSIGN_UUID);

    private final String key;

    private final IdType idType;

    private IdTypeEnum(String key, IdType idType) {
        this.key = key;
        this.idType = idType;
    }

    public String getKey() {
        return key;
    }

    public IdType getIdType() {
        return idType;
    }

    public static IdType getIdType(String key) {
        for (IdTypeEnum value : IdTypeEnum.values()) {
            if (value.getKey().equals(key)) {
                return value.getIdType();
            }
        }
        return null;
    }
}
