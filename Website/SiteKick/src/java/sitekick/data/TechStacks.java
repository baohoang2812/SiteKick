/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gia Bảo Hoàng
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TechStacks")
public class TechStacks {

    public TechStacks() {
    }
    @XmlElement(required = true, name = "TechStack")
    protected List<TechStack> stackList;

    public List<TechStack> getStackList() {
        return stackList;
    }

    public void setStackList(List<TechStack> stackList) {
        this.stackList = stackList;
    }
}
