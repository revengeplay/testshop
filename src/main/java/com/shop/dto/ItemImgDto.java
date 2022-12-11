package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Data;
import org.modelmapper.ModelMapper;

/**
 * A DTO for the {@link com.shop.entity.ItemImg} entity
 */
@Data
public class ItemImgDto {
    private final String imgName;
    private final String oriImgName;
    private final String imgUrl;
    private final String repimgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg){
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
