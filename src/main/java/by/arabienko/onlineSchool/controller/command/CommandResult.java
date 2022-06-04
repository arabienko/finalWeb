package by.arabienko.onlineSchool.controller.command;

import java.util.Objects;

/**
 * The type command result
 */
public class CommandResult {
    private String page;
    private boolean isRedirect;

    public CommandResult() {
    }

    public CommandResult(String page) {
        this.page = page;
        isRedirect = false;
    }

    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Is redirect boolean.
     *
     * @return the boolean
     */
    public boolean isRedirect() {
        return isRedirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof CommandResult)) return false;
        CommandResult that = (CommandResult) o;
        return isRedirect==that.isRedirect
                && Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, isRedirect);
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "page='" + page + '\'' +
                ", isRedirect=" + isRedirect +
                '}';
    }
}
