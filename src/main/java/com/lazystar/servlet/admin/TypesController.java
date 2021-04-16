package com.lazystar.servlet.admin;

import com.lazystar.pojo.Type;
import com.lazystar.service.TypesService;
import com.lazystar.service.UserService;
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
public class TypesController {

    @Autowired
    private TypesService typesService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Type> typePage=typesService.listType(pageable);
        model.addAttribute("page",typePage);

        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteTypes(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        typesService.deleteType(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/input")
    public String updateTypes(@PathVariable Long id, Model model){
        model.addAttribute("type",typesService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1=typesService.getTypeByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","已经存在该分类名称");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t=typesService.saveType(type);
        if (t==null){
            attributes.addFlashAttribute("message","添加失败");
        }else {
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String updatePost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type type1=typesService.getTypeByName(type.getName());
        if (type1!=null){
            result.rejectValue("name","nameError","已经存在该分类名称");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t=typesService.updateType(id, type);
        if (t==null){
            attributes.addFlashAttribute("message","修改失败");
        }else {
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/types";
    }

}
