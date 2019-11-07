package controller;

import model.PresencasEquipeSub;
import controller.util.JsfUtil;
import controller.util.PaginationHelper;
import model.PresencasEquipeSubFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("presencasEquipeSubController")
@SessionScoped
public class PresencasEquipeSubController implements Serializable {

    private PresencasEquipeSub current;
    private DataModel items = null;
    @EJB
    private model.PresencasEquipeSubFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PresencasEquipeSubController() {
    }

    public PresencasEquipeSub getSelected() {
        if (current == null) {
            current = new PresencasEquipeSub();
            current.setPresencasEquipeSubPK(new model.PresencasEquipeSubPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private PresencasEquipeSubFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (PresencasEquipeSub) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PresencasEquipeSub();
        current.setPresencasEquipeSubPK(new model.PresencasEquipeSubPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getPresencasEquipeSubPK().setIdsubevento(current.getSubeventos().getIdsubevento());
            current.getPresencasEquipeSubPK().setIdequipe(current.getEquipes().getIdequipe());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PresencasEquipeSubCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (PresencasEquipeSub) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getPresencasEquipeSubPK().setIdsubevento(current.getSubeventos().getIdsubevento());
            current.getPresencasEquipeSubPK().setIdequipe(current.getEquipes().getIdequipe());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PresencasEquipeSubUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PresencasEquipeSub) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PresencasEquipeSubDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public PresencasEquipeSub getPresencasEquipeSub(model.PresencasEquipeSubPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = PresencasEquipeSub.class)
    public static class PresencasEquipeSubControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PresencasEquipeSubController controller = (PresencasEquipeSubController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "presencasEquipeSubController");
            return controller.getPresencasEquipeSub(getKey(value));
        }

        model.PresencasEquipeSubPK getKey(String value) {
            model.PresencasEquipeSubPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new model.PresencasEquipeSubPK();
            key.setIdequipe(Integer.parseInt(values[0]));
            key.setIdsubevento(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(model.PresencasEquipeSubPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdequipe());
            sb.append(SEPARATOR);
            sb.append(value.getIdsubevento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PresencasEquipeSub) {
                PresencasEquipeSub o = (PresencasEquipeSub) object;
                return getStringKey(o.getPresencasEquipeSubPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PresencasEquipeSub.class.getName());
            }
        }

    }

}
