package domain;

import common.utils.StringUtils;

public class Participant {

    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    private Participant(String name) {
        this.name = name;
    }

    public static Participant from(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("참가자 이름을 입력해 주세요.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("참가자 이름은 " + MAX_NAME_LENGTH + " 자 이하여야 합니다.");
        }
        return new Participant(name);
    }

    public String name() {
        return this.name;
    }
}