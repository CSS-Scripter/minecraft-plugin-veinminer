package com.veinminer.css.enums;

public enum ConfigPathEnum {
    BLOCK_WHITELIST("blockWhitelist"),
    BLOCK_LIMIT("veinMineLimit");

    public final String path;
    private ConfigPathEnum(String path) {
        this.path = path;
    }

    public String toString() {
        return this.path;
    }
}
