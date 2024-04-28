package com.plutossy.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.plutossy.service.BlogService;
import com.plutossy.utils.Consts;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    // 添加博客
    @RequestMapping(value = "/manage/insertBlog", method = RequestMethod.POST)
    public Object insertBlog(@RequestBody Map<String, Object> jsonData) {
        String title = jsonData.get("title") == null ? "" : (String) jsonData.get("title");
        String content = jsonData.get("content") == null ? "" : (String) jsonData.get("content");
        String description = jsonData.get("description") == null ? "暂无描述" : (String) jsonData.get("description");
        String picture = jsonData.get("picture") == null ? "" : (String) jsonData.get("picture");
        Integer flag = jsonData.get("flag") == null ? 0 : (Integer) jsonData.get("flag");
        Boolean recommend = jsonData.get("recommend") != null && (Boolean) jsonData.get("recommend");
        Integer published = jsonData.get("published") == null ? 1 : (Integer) jsonData.get("published");
        Boolean commentabled = jsonData.get("commentabled") == null || (Boolean) jsonData.get("commentabled");
        Boolean shared = jsonData.get("shared") == null || (Boolean) jsonData.get("shared");
        // Integer views = jsonData.get("views") == null ? 0 : (Integer) jsonData.get("views");
        Long typeId = jsonData.get("typeId") == null ? -1 : (Long) jsonData.get("typeId");
        Long userId = jsonData.get("userId") == null ? -1 : (Long) jsonData.get("userId");
        Boolean insertFlag = blogService.insertBlog(title, content, description, picture, flag, recommend, published, commentabled, shared, typeId, userId);
        JSONObject jsonObject = new JSONObject();
        if (title.isEmpty()) {
            jsonObject.put(Consts.CODE, 400);
            jsonObject.put(Consts.MSG, "标题不能为空！");
        }
        if (content.isEmpty()) {
            jsonObject.put(Consts.CODE, 401);
            jsonObject.put(Consts.MSG, "内容不能为空！");
        }
        if (insertFlag) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "添加成功！");
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "添加失败！");
        }
        return jsonObject;
    }

    // 根据id删除博客
    @RequestMapping(value = "/manage/deleteBlog", method = RequestMethod.POST)
    public Object deleteBlogById(@RequestBody String id) {
        Long delId = Long.valueOf(id);
        Boolean deleteFlag = blogService.deleteBlogById(delId);
        JSONObject jsonObject = new JSONObject();
        if (deleteFlag) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "删除成功！");
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "删除失败！");
        }
        return jsonObject;
    }

    // 批量删除博客
    @RequestMapping(value = "/manage/deleteBlogs", method = RequestMethod.POST)
    public Object deleteBlogs(@RequestBody String[] ids) {
        JSONObject jsonObject = new JSONObject();
        for (String id : ids) {
            Boolean flag = blogService.deleteBlogById(Long.valueOf(id));
            if (!flag) {
                jsonObject.put(Consts.CODE, 500);
                jsonObject.put(Consts.MSG, "删除失败！");
                return jsonObject;
            }
        }
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "删除成功！");
        return jsonObject;
    }

    // 修改博客
    @RequestMapping(value = "/manage/updateBlog", method = RequestMethod.POST)
    public Object updateBlog(@RequestBody Map<String, Object> jsonData) {
        Long id = Long.valueOf(jsonData.get("id").toString());
        String title = jsonData.get("title").toString();
        String content = jsonData.get("content").toString();
        String description = jsonData.get("description") == null ? "暂无描述" : jsonData.get("description").toString();
        String picture = jsonData.get("picture") == null ? "" : jsonData.get("picture").toString();
        Integer flag = jsonData.get("flag") == null ? 0 : (Integer) jsonData.get("flag");
        Boolean recommend = jsonData.get("recommend") != null && (Boolean) jsonData.get("recommend");
        Integer published = jsonData.get("published") == null ? 1 : (Integer) jsonData.get("published");
        Boolean commentabled = jsonData.get("commentabled") == null || (Boolean) jsonData.get("commentabled");
        Boolean shared = jsonData.get("shared") == null || (Boolean) jsonData.get("shared");
        Long typeId = jsonData.get("typeId") == null ? -1 : (Long) jsonData.get("typeId");
        Long userId = jsonData.get("userId") == null ? -1 : (Long) jsonData.get("userId");
        Boolean updateFlag = blogService.updateBlog(id, title, content, description, picture, flag, recommend, published, commentabled, shared, typeId, userId);
        JSONObject jsonObject = new JSONObject();
        if (title.isEmpty()) {
            jsonObject.put(Consts.CODE, 400);
            jsonObject.put(Consts.MSG, "标题不能为空！");
        }
        if (content.isEmpty()) {
            jsonObject.put(Consts.CODE, 401);
            jsonObject.put(Consts.MSG, "内容不能为空！");
        }
        if (updateFlag) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "修改成功！");
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "修改失败！");
        }
        return jsonObject;
    }

    // 根据id查询博客
    @RequestMapping(value = "/manage/selectBlogById", method = RequestMethod.POST)
    public Object selectBlogById(@RequestBody String id) {
        Long selectId = Long.valueOf(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.DATA, blogService.selectBlogById(selectId));
        return jsonObject;
    }

    // 查询所有博客
    @RequestMapping(value = "/manage/selectAllBlog", method = RequestMethod.POST)
    public Object selectAllBlog(@RequestBody Map<String, Object> jsonData) {
        Integer pageNum = jsonData.get("pageNum") == null ? 1 : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? 10 : (Integer) jsonData.get("pageSize");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.DATA, blogService.selectAllBlog(pageNum, pageSize));
        return jsonObject;
    }

    // 根据条件查询博客
    @RequestMapping(value = "/manage/selectBlogByQuery", method = RequestMethod.POST)
    public Object selectBlogByQuery(@RequestBody Map<String, Object> jsonData) {
        Integer pageNum = jsonData.get("pageNum") == null ? 1 : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? 10 : (Integer) jsonData.get("pageSize");
        String queryParam = jsonData.get("queryParam") == null ? "" : (String) jsonData.get("queryParam");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.DATA, blogService.selectBlogByQuery(pageNum, pageSize, queryParam));
        return jsonObject;
    }
}
