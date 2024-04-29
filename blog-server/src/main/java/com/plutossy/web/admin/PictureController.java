package com.plutossy.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Picture;
import com.plutossy.service.PictureService;
import com.plutossy.utils.Consts;
import com.plutossy.utils.PageDefault;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @NotNull
    private Object getObject(JSONObject jsonObject, PageInfo<Picture> pageData) {
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "查询成功！");
        jsonObject.put(Consts.TOTAL, pageData.getTotal());
        jsonObject.put(Consts.PAGES, pageData.getPages());
        jsonObject.put(Consts.PAGE_NUM, pageData.getPageNum());
        jsonObject.put(Consts.PAGE_SIZE, pageData.getPageSize());
        jsonObject.put(Consts.DATA, pageData.getList());
        return jsonObject;
    }

    @RequestMapping(value = "/manage/selectPictureByQuery", method = RequestMethod.POST)
    public Object selectPictureByQuery(@RequestBody Map<String, Object> jsonData) {
        Integer pageNum = jsonData.get("pageNum") == null ? PageDefault.PAGE_NUM : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? PageDefault.PAGE_SIZE : (Integer) jsonData.get("pageSize");
        String queryParam = jsonData.get("queryParam") == null ? "" : (String) jsonData.get("queryParam");
        JSONObject jsonObject = new JSONObject();
        PageInfo<Picture> pageData = pictureService.selectPictureByQuery(pageNum, pageSize, queryParam);
        return getObject(jsonObject, pageData);
    }

    @RequestMapping(value = "/manage/selectPictureById", method = RequestMethod.GET)
    public Object selectPictureById(@RequestParam("id") Long id) {
        JSONObject jsonObject = new JSONObject();
        Picture pictureList = pictureService.selectPictureById(id);
        if (pictureList != null) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "查询成功！");
            jsonObject.put(Consts.DATA, pictureList);
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "查询失败！");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/manage/addPicture", method = RequestMethod.POST)
    public Object insertPicture(@RequestBody Map<String, Object> jsonData) {
        String url = jsonData.get("url") == null ? "" : (String) jsonData.get("url");
        String name = jsonData.get("name") == null ? "" : (String) jsonData.get("name");
        String description = jsonData.get("description") == null ? "" : (String) jsonData.get("description");
        Boolean flag = pictureService.insertPicture(url, name, description);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "添加成功！");
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "添加失败！");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/manage/deletePicture", method = RequestMethod.POST)
    public Object deletePicture(@RequestBody String id) {
        Long delId = Long.valueOf(id);
        Boolean flag = pictureService.deletePictureById(delId);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "删除成功！");
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "删除失败！");
        }
        return jsonObject;
    }

    // 批量删除用户 参数为用户id数组
    @RequestMapping(value = "/manage/deletePictures", method = RequestMethod.POST)
    public Object deleteUsers(@RequestBody String[] ids) {
        JSONObject jsonObject = new JSONObject();
        for (String id : ids) {
            Boolean flag = pictureService.deletePictureById(Long.valueOf(id));
            if (!flag) {
                jsonObject.put(Consts.CODE, 400);
                jsonObject.put(Consts.MSG, "删除失败！");
                return jsonObject;
            }
        }
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "删除成功！");
        return jsonObject;
    }

    // 修改照片
    @RequestMapping(value = "/manage/updatePicture", method = RequestMethod.POST)
    public Object updatePicture(@RequestBody Map<String, Object> jsonData) {
        Long id = Long.valueOf(jsonData.get("id").toString());
        String url = jsonData.get("url").toString();
        String name = jsonData.get("name").toString();
        String description = jsonData.get("description")== null ? "暂无描述" : jsonData.get("description").toString();
        Boolean flag = pictureService.updatePicture(id, url, name, description);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "修改成功！");
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "修改失败！");
        }
        return jsonObject;
    }
}
