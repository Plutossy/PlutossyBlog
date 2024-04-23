package com.plutossy.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Tag;
import com.plutossy.service.TagService;
import com.plutossy.utils.Consts;
import com.plutossy.utils.PageDefault;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @NotNull
    private Object getObject(JSONObject jsonObject, PageInfo<Tag> pageData) {
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "查询成功！");
        jsonObject.put(Consts.TOTAL, pageData.getTotal());
        jsonObject.put(Consts.PAGES, pageData.getPages());
        jsonObject.put(Consts.PAGE_NUM, pageData.getPageNum());
        jsonObject.put(Consts.PAGE_SIZE, pageData.getPageSize());
        jsonObject.put(Consts.DATA, pageData.getList());
        return jsonObject;
    }

    @RequestMapping(value = "/manage/tagList", method = RequestMethod.POST)
    public Object selectAllTag(@RequestBody Map<String, Object> jsonData) {
        Integer pageNum = jsonData.get("pageNum") == null ? PageDefault.PAGE_NUM : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? PageDefault.PAGE_SIZE : (Integer) jsonData.get("pageSize");
        JSONObject jsonObject = new JSONObject();
        PageInfo<Tag> pageData = tagService.selectAllTag(pageNum, pageSize);
        return getObject(jsonObject, pageData);
    }

    @RequestMapping(value = "/manage/selectTagByName", method = RequestMethod.POST)
    public Object selectTagByName(@RequestBody Map<String, Object> jsonData) {
        Integer pageNum = jsonData.get("pageNum") == null ? PageDefault.PAGE_NUM : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? PageDefault.PAGE_SIZE : (Integer) jsonData.get("pageSize");
        String name = jsonData.get("name") == null ? "" : (String) jsonData.get("name");
        JSONObject jsonObject = new JSONObject();
        PageInfo<Tag> pageData = tagService.selectTagByName(pageNum, pageSize, name);
        return getObject(jsonObject, pageData);
    }

    @RequestMapping(value = "/manage/selectTagById", method = RequestMethod.GET)
    public Object selectTagById(@RequestParam("id") Long id) {
        JSONObject jsonObject = new JSONObject();
        Tag tag = tagService.selectTagById(id);
        if (tag != null) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "查询成功！");
            jsonObject.put(Consts.DATA, tag);
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "查询失败！");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/manage/addTag", method = RequestMethod.POST)
    public Object insertTag(@RequestBody Map<String, Object> jsonData) {
        String name = jsonData.get("name") == null ? "" : (String) jsonData.get("name");
        // 标签名称不能重复
        if (tagService.selectNameCount(name)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "标签名称已存在！");
            return jsonObject;
        }
        Boolean flag = tagService.insertTag(name);
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

    @RequestMapping(value = "/manage/deleteTag", method = RequestMethod.POST)
    public Object deleteTag(@RequestBody String id) {
        Long delId = Long.valueOf(id);
        Boolean flag = tagService.deleteTagById(delId);
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
    @RequestMapping(value = "/manage/deleteTags", method = RequestMethod.POST)
    public Object deleteUsers(@RequestBody String[] ids) {
        JSONObject jsonObject = new JSONObject();
        for (String id : ids) {
            Boolean flag = tagService.deleteTagById(Long.valueOf(id));
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

    // 修改类型
    @RequestMapping(value = "/manage/updateTag", method = RequestMethod.POST)
    public Object updateTag(@RequestBody Map<String, Object> jsonData) {
        Long id = Long.valueOf(jsonData.get("id").toString());
        String name = jsonData.get("name").toString();
        Boolean flag = tagService.updateTag(id, name);
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
