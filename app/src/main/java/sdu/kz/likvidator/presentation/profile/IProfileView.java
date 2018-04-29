package sdu.kz.likvidator.presentation.profile;

import sdu.kz.likvidator.presentation.base.IBaseView;

public interface IProfileView extends IBaseView{
    public void setName(String name);
    public void setSurname(String surname);
    public void setImage(String imageUrl);
    public void setEmail(String email);
}
