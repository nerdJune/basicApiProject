package io.jh.main.enums;

import lombok.Getter;

public enum BoardSearchTypeEnum {

    TITLE(1, "제목", "TITLE", "게시글 제목"),
    CONTENT(2, "내용", "CONTENT", "게시글 내용"),
    WRITER(3, "작성자", "WRITER", "게시글 작성자");

    @Getter
    private final int value;
    @Getter
    private final String nameKr;
    @Getter
    private final String nameEn;
    private final String description;

    BoardSearchTypeEnum(int value, String nameKr, String nameEn, String description) {
        this.value = value;
        this.nameKr = nameKr;
        this.nameEn = nameEn;
        this.description = description;
    }

}
