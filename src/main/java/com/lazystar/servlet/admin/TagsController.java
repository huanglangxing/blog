package com.lazystar.servlet.admin;

import com.lazystar.pojo.Tag;
import com.lazystar.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Tag> tagsPage=tagsService.listTag(pageable);
        model.addAttribute("page",tagsPage);

        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTags(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tagsService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }


    @GetMapping("/tags/{id}/input")
    public String updateTags(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagsService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag type, BindingResult result, RedirectAttributes attributes){
        Tag type1=tagsService.getTagByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","已经存在该分类名称");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t=tagsService.saveTag(type);
        if (t==null){
            attributes.addFlashAttribute("message","添加失败");
        }else {
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String updatePost(@Valid Tag type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Tag type1=tagsService.getTagByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","已经存在该分类名称");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t=tagsService.updateTag(id, type);
        if (t==null){
            attributes.addFlashAttribute("message","修改失败");
        }else {
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/tags";
    }

}