package com.plutossy.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Type;
import com.plutossy.service.TypeService;
import com.plutossy.utils.Consts;
import com.plutossy.utils.PageDefault;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    @NotNull
    private Object getObject(JSONObject jsonObject, PageInfo<Type> pageData) {
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "查询成功！");
        jsonObject.put(Consts.TOTAL, pageData.getTotal());
        jsonObject.put(Consts.PAGES, pageData.getPages());
        jsonObject.put(Consts.PAGE_NUM, pageData.getPageNum());
        jsonObject.put(Consts.PAGE_SIZE, pageData.getPageSize());
        jsonObject.put(Consts.DATA, pageData.getList());
        return jsonObject;
    }

    @RequestMapping(value = "/manage/typeList", method = RequestMethod.POST)
    public Object selectAllType(@RequestBody Map<String, Object> jsonData) {
        String type = jsonData.get("type") == null ? "" : (String) jsonData.get("type");
        Integer pageNum = jsonData.get("pageNum") == null ? PageDefault.PAGE_NUM : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? PageDefault.PAGE_SIZE : (Integer) jsonData.get("pageSize");
        if (Objects.equals(type, "all")) {
            pageNum = 1;
            pageSize = -1;
        }
        JSONObject jsonObject = new JSONObject();
        PageInfo<Type> pageData = typeService.selectAllType(pageNum, pageSize);
        return getObject(jsonObject, pageData);
    }

    @RequestMapping(value = "/manage/selectTypeByName", method = RequestMethod.POST)
    public Object selectTypeByName(@RequestBody Map<String, Object> jsonData) {
        Integer pageNum = jsonData.get("pageNum") == null ? PageDefault.PAGE_NUM : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? PageDefault.PAGE_SIZE : (Integer) jsonData.get("pageSize");
        String name = jsonData.get("name") == null ? "" : (String) jsonData.get("name");
        JSONObject jsonObject = new JSONObject();
        PageInfo<Type> pageData = typeService.selectTypeByName(pageNum, pageSize, name);
        return getObject(jsonObject, pageData);
    }

    @RequestMapping(value = "/manage/selectTypeById", method = RequestMethod.GET)
    public Object selectTypeById(@RequestParam("id") Long id) {
        JSONObject jsonObject = new JSONObject();
        Type type = typeService.selectTypeById(id);
        if (type != null) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "查询成功！");
            jsonObject.put(Consts.DATA, type);
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "查询失败！");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/manage/addType", method = RequestMethod.POST)
    public Object insertType(@RequestBody Map<String, Object> jsonData) {
        String name = jsonData.get("name") == null ? "" : (String) jsonData.get("name");
        // 分类名称不能重复
        if (typeService.selectNameCount(name)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "分类名称已存在！");
            return jsonObject;
        }
        Boolean flag = typeService.insertType(name);
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

    @RequestMapping(value = "/manage/deleteType", method = RequestMethod.POST)
    public Object deleteType(@RequestBody String id) {
        Long delId = Long.valueOf(id);
        Boolean flag = typeService.deleteTypeById(delId);
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
    @RequestMapping(value = "/manage/deleteTypes", method = RequestMethod.POST)
    public Object deleteUsers(@RequestBody String[] ids) {
        JSONObject jsonObject = new JSONObject();
        for (String id : ids) {
            Boolean flag = typeService.deleteTypeById(Long.valueOf(id));
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
    @RequestMapping(value = "/manage/updateType", method = RequestMethod.POST)
    public Object updateType(@RequestBody Map<String, Object> jsonData) {
        Long id = Long.valueOf(jsonData.get("id").toString());
        String name = jsonData.get("name").toString();
        Boolean flag = typeService.updateType(id, name);
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
