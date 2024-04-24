package com.plutossy.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.plutossy.domain.Music;
import com.plutossy.service.MusicService;
import com.plutossy.utils.Consts;
import com.plutossy.utils.PageDefault;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MusicController {
    @Autowired
    private MusicService musicService;

    @NotNull
    private Object getObject(JSONObject jsonObject, PageInfo<Music> pageData) {
        jsonObject.put(Consts.CODE, 200);
        jsonObject.put(Consts.MSG, "查询成功！");
        jsonObject.put(Consts.TOTAL, pageData.getTotal());
        jsonObject.put(Consts.PAGES, pageData.getPages());
        jsonObject.put(Consts.PAGE_NUM, pageData.getPageNum());
        jsonObject.put(Consts.PAGE_SIZE, pageData.getPageSize());
        jsonObject.put(Consts.DATA, pageData.getList());
        return jsonObject;
    }

    @RequestMapping(value = "/manage/musicList", method = RequestMethod.POST)
    public Object selectAllMusic(@RequestBody Map<String, Object> jsonData) {
        Integer pageNum = jsonData.get("pageNum") == null ? PageDefault.PAGE_NUM : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? PageDefault.PAGE_SIZE : (Integer) jsonData.get("pageSize");
        JSONObject jsonObject = new JSONObject();
        PageInfo<Music> pageData = musicService.selectAllMusic(pageNum, pageSize);
        return getObject(jsonObject, pageData);
    }

    @RequestMapping(value = "/manage/selectMusicByQuery", method = RequestMethod.POST)
    public Object selectMusicByQuery(@RequestBody Map<String, Object> jsonData) {
        Integer pageNum = jsonData.get("pageNum") == null ? PageDefault.PAGE_NUM : (Integer) jsonData.get("pageNum");
        Integer pageSize = jsonData.get("pageSize") == null ? PageDefault.PAGE_SIZE : (Integer) jsonData.get("pageSize");
        String queryParam = jsonData.get("queryParam") == null ? "" : (String) jsonData.get("queryParam");
        JSONObject jsonObject = new JSONObject();
        PageInfo<Music> pageData = musicService.selectMusicByQuery(pageNum, pageSize, queryParam);
        return getObject(jsonObject, pageData);
    }

    @RequestMapping(value = "/manage/selectMusicById", method = RequestMethod.GET)
    public Object selectMusicById(@RequestParam("id") Long id) {
        JSONObject jsonObject = new JSONObject();
        Music musicList = musicService.selectMusicById(id);
        if (musicList != null) {
            jsonObject.put(Consts.CODE, 200);
            jsonObject.put(Consts.MSG, "查询成功！");
            jsonObject.put(Consts.DATA, musicList);
        } else {
            jsonObject.put(Consts.CODE, 500);
            jsonObject.put(Consts.MSG, "查询失败！");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/manage/addMusic", method = RequestMethod.POST)
    public Object insertMusic(@RequestBody Map<String, Object> jsonData) {
        String name = jsonData.get("name") == null ? "" : (String) jsonData.get("name");
        String url = jsonData.get("url") == null ? "" : (String) jsonData.get("url");
        String singer = jsonData.get("singer") == null ? "" : (String) jsonData.get("singer");
        String img = jsonData.get("img") == null ? "" : (String) jsonData.get("img");
        String lyric = jsonData.get("lyric") == null ? "" : (String) jsonData.get("lyric");
        Boolean flag = musicService.insertMusic(name, url, singer, img, lyric);
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

    @RequestMapping(value = "/manage/deleteMusic", method = RequestMethod.POST)
    public Object deleteMusic(@RequestBody String id) {
        Long delId = Long.valueOf(id);
        Boolean flag = musicService.deleteMusicById(delId);
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
    @RequestMapping(value = "/manage/deleteMusics", method = RequestMethod.POST)
    public Object deleteUsers(@RequestBody String[] ids) {
        JSONObject jsonObject = new JSONObject();
        for (String id : ids) {
            Boolean flag = musicService.deleteMusicById(Long.valueOf(id));
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

    // 修改音乐
    @RequestMapping(value = "/manage/updateMusic", method = RequestMethod.POST)
    public Object updateMusic(@RequestBody Map<String, Object> jsonData) {
        Long id = Long.valueOf(jsonData.get("id").toString());
        String name = jsonData.get("name").toString();
        String url = jsonData.get("url").toString();
        String singer = jsonData.get("singer").toString();
        String img = jsonData.get("img").toString();
        String lyric = jsonData.get("lyric").toString();
        Boolean flag = musicService.updateMusic(id, name, url, singer, img, lyric);
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
