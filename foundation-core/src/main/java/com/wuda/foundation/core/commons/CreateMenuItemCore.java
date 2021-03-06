package com.wuda.foundation.core.commons;

import lombok.Getter;

import java.util.Objects;

/**
 * for create menu item.
 *
 * @author wuda
 * @since 1.0.3
 */
@Getter
public class CreateMenuItemCore {

    private Long id;
    private Long menuItemId;
    private String name;
    private String description;
    private Long opUserId;

    /**
     * 禁止实例化,使用{@link Builder}实例化.
     */
    private CreateMenuItemCore() {

    }

    /**
     * 用于创建{@link CreateMenuItemCore}.
     *
     * @author wuda
     * @since 1.0.0
     */
    public static class Builder implements com.wuda.foundation.lang.Builder<CreateMenuItemCore> {

        private Long id;
        private Long menuItemId;
        private String name;
        private String description;
        private Long opUserId;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setMenuItemId(Long menuItemId) {
            this.menuItemId = menuItemId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setOpUserId(Long opUserId) {
            this.opUserId = opUserId;
            return this;
        }

        @Override
        public CreateMenuItemCore build() {
            CreateMenuItemCore createMenuItemCore = new CreateMenuItemCore();
            createMenuItemCore.id = Objects.requireNonNull(this.id);
            createMenuItemCore.menuItemId = Objects.requireNonNull(this.menuItemId);
            createMenuItemCore.name = Objects.requireNonNull(this.name);
            createMenuItemCore.description = description;
            createMenuItemCore.opUserId = Objects.requireNonNull(opUserId);
            return createMenuItemCore;
        }
    }
}
