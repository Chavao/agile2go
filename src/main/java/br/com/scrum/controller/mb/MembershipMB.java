package br.com.scrum.controller.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.scrum.entity.User;
import br.com.scrum.entity.enums.UserRole;
import br.com.scrum.service.UserService;
import br.com.scrum.util.exception.BusinessException;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class MembershipMB extends BaseBean
{
	@Inject private UserService userService;
	
	private User membership = new User();
	private List<User> memberships = new ArrayList<User>();
	private List<SelectItem> membershipsItems;
	
	@PostConstruct
	public void init()
	{
		findAll();
	}
	
	public void createOrSave()
	{
		try {
			if (membership.getId() == null) {
				userService.create(membership);
			} else {
				userService.save(membership);
			}
			findAll();
		} catch (BusinessException be) {
			addErrorMessage(be.getMessage());
			be.printStackTrace();
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void delete()
	{
		try {
			userService.delete(membership);
			findAll();
			addInfoMessage("membership removed");
		} catch ( Exception e ) {
			addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<SelectItem> getMembershipsItems()
	{
		if (membershipsItems == null) {
			membershipsItems = new ArrayList<SelectItem>();
			membershipsItems.add(new SelectItem(null, ""));
			for (UserRole u : UserRole.values()) {
				membershipsItems.add(new SelectItem(u, u.getDescription()));
			}
		}
		return membershipsItems;
	}

	public void findAll ()
	{
		memberships = userService.findAll();
	}

	public User getMembership()
	{
		return membership;
	}

	public void setMembership(User membership)
	{
		this.membership = membership;
	}

	public List<User> getMemberships()
	{
		return memberships;
	}

	public void setMemberships(List<User> memberships)
	{
		this.memberships = memberships;
	}
	
}
