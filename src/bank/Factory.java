package bank;
public interface Factory<T extends Manageable> {
    T create();
}
