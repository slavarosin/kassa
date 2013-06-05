package com.eb.kassa.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.kassa.beans.Comment;
import com.eb.kassa.service.CommentService;

@Controller
@RequestMapping("/manage/comments")
public class CommentsController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		model.addAttribute("comments", commentService.getComments());

		return "comments";
	}

	@RequestMapping(method = RequestMethod.GET, params = { "remove" })
	public String removeComment(@RequestParam("remove")
	final Integer id, final ModelMap model) {

		commentService.remove(id);

		return setupForm(model);
	}

	@RequestMapping(method = RequestMethod.POST, params = { "text" })
	public String addComment(@RequestParam("text")
	final String text, final ModelMap model) {

		Comment comment = new Comment();
		comment.setText(text);
		commentService.addComment(comment);

		return setupForm(model);
	}
}