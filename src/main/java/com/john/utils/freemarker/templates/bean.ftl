package ${classPath};

public class ${className} {

    //属性
    private ${attributeType_a} ${attribute_a};

    private ${attributeType_b} ${attribute_b};

    private ${attributeType_c} ${attribute_c};

    //和前端传来的String字符串产生联系
    private ${attributeType_x} ${attribute_x};

    //set,get方法
    public void set${upperCase_a}(${attributeType_a} ${attribute_a}) {

        this.${attribute_a} = ${attribute_a};
    }

    public void set${upperCase_b}(${attributeType_b} ${attribute_b}) {

        this.${attribute_b} = ${attribute_b};
    }

    public void set${upperCase_c}(${attributeType_c} ${attribute_c}) {

        this.${attribute_c} = ${attribute_c};
    }

    public void set${upperCase_x}(${attributeType_x} ${attribute_x}) {

        this.${attribute_x} = ${attribute_x};
    }

    public ${attributeType_a} get${upperCase_a}() {

        return ${attribute_a};
    }

    public ${attributeType_b} get${upperCase_b}() {

         return ${attribute_b};
    }

    public ${attributeType_c} get${upperCase_c}() {

        return ${attribute_c};
    }

    public ${attributeType_x} get${upperCase_x}() {

        return ${attribute_x};
    }

    @Override
    public String toString() {
        return "${className} [" +
        "${attribute_a}='" + ${attribute_a} + '\'' +
        ", ${attribute_b}='" + ${attribute_b} + '\'' +
        ", ${attribute_c}='" + ${attribute_c} + '\'' +
        ", ${attribute_x}=" + ${attribute_x} +
        ']';
    }


}