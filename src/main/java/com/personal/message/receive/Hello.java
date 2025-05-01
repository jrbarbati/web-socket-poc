package com.personal.message.receive;

public class Hello extends ReceivableMessage
{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static class Builder
    {
        private final Hello message = new Hello();

        public Builder name(String name)
        {
            message.setName(name);
            return this;
        }

        public Hello build()
        {
            return message;
        }
    }
}
