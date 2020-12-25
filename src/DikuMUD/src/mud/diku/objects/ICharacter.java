/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mud.diku.objects;

/**
 * This represents a contract that any Class or Interface must implement these
 *  methods.
 * @author goldbishop
 */
public interface ICharacter {
    public String getName();
    public IGuild getGuild();
    //TODO: public IStorage getBank();
    public IStorage getInventory(); 
    public IRace getRace();
    public ESex getSex();
}
