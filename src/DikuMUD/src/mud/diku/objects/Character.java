/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mud.diku.objects;

/**
 * Base-class for all Characters within the Server.
 *
 * @author goldbishop
 */
public abstract class Character implements ICharacter, IName, IIdentifier {

    private String _name;
    private IGuild _guild;
    private IStorage _inventory;
    private long _number;
    private IRace _race;
    private ESex _sex;

    /**
     * The Name of the character
     *
     * @return String value; represents the formal Name of the character
     */
    @Override
    public String getName() {
        return this._name;
    }

    /**
     * *
     * The Guild (Class) of the Character
     *
     * @return IGuild Object
     */
    @Override
    public IGuild getGuild() {
        return this._guild;
    }

    /**
     * *
     * The Character's Inventory - For special NPC's this can be their custom
     * container of content; for example, a Shop keeper's List of Goods
     *
     * @return IStorage Object
     */
    @Override
    public IStorage getInventory() {
        return this._inventory;
    }

    /**
     * *
     * The Character's Race
     *
     * @return IRace Object
     */
    @Override
    public IRace getRace() {
        return this._race;
    }

    /**
     * *
     * The Character's Sex
     *
     * @return ESex Enum Type
     */
    @Override
    public ESex getSex() {
        return this._sex;
    }

    /**
     * *
     * The Character's Unique Identifier
     *
     * @return Long value
     */
    @Override
    public long getNumber() {
        return this._number;
    }

}
