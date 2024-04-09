package bussiness.design;

public interface IGeneric <T, E>{

    T findById(E id);

    void addElement();

    void editElement();
    void deleteElement();

    //ko để displayAllData làm static, vì các stream cần truy cập để hiển thị

}
