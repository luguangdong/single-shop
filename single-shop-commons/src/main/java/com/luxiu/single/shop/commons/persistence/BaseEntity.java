package com.luxiu.single.shop.commons.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>
 * Description: 实体类的基类
 * </p>
 *
 * @author luguangdong
 * @version 1.0
 * @ClassName BaseEntity
 * @date 2020/5/2 18:25
 * @company https://www.singlewindow.cn/
 */
@Data
public abstract class BaseEntity implements Serializable {
    private Long id;
    private Date created;
    private Date updated;
}
