package com.howie.story;

import com.howie.story.api.dto.UserDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午5:56 2018/9/7
 * @Modified by:xiaohaoyun
 */
public class Main {
    public static void main(String[] args) {
//        double[] arr ={1,8,7,3,5,9};
//        double sum = sum(arr);
//        int d =(int)(sum % arr.length);
//        System.out.println(d);

        List<UserDTO> list = new ArrayList<>();
        UserDTO dto1 = new UserDTO();
        dto1.setId(1);
        dto1.setName("xiao");
        list.add(dto1);

        UserDTO dto2 = new UserDTO();
        dto2.setId(2);
        dto2.setName("xiao");
        list.add(dto2);

        UserDTO dto3 = new UserDTO();
        dto3.setId(3);
        dto3.setName("yang");
        list.add(dto3);

        Map<String,List<UserDTO>> map = list.stream().collect(Collectors.toMap(dto->dto.getName(),dto-> Arrays.asList(dto)));
        System.out.println(map);




    }

    public static double sum (double[] arr) {
        double sum =0;
        for (int i = 0;i<arr.length;i++) {
            sum+=arr[i];
        }

        return sum;
    }

}
