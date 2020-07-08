/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.map;

import java.util.List;

/**
 *
 * @author Gia Bảo Hoàng
 */
public interface IMap<S,D> {
    D map(S jaxbObject);
    List<D> mapList(List<S> jaxbObjectList);
}
