package com.flippy.flipper.item;

import com.flippy.moteur.item.Item;
import com.flippy.moteur.physique.force.Forces;

/**
 * Gathers a set of item that can interact with each other
 */
public class Items {

	/**
	 * Gather all objects to which no force applies
	 *
	 * @see Item
	 */
	private Item[] items = new Item[0];

	/**
	 * Gather all the objects to which force applies
	 *
	 * @see Item
	 */
	private Item[] itemsMovable = new Item[0];;

	/**
	 * Add item
	 * 
	 * @param i item to add
	 * @see Item
	 */
	public void addItem(Item i) {
		Item[] tmp = new Item[0];
		if (i.movable())
			tmp = itemsMovable;
		else
			tmp = items;
		Item[] ret = new Item[tmp.length + 1];
		ret[0] = i;
		for (int x = 1; x < ret.length; x++) {
			ret[x] = tmp[x - 1];
		}
		if (i.movable())
			itemsMovable = ret;
		else
			items = ret;
	}

	/**
	 * Browse all moving items and calculate the forces applied to each of them and
	 * then change their position
	 * 
	 * @param t refresh time
	 */
	public void verification(double t) {
		for (int i = 0; i < itemsMovable.length; i++) {
			for (int j = 0; j < items.length; j++) {
				Forces.action(itemsMovable[i], items[j], t);
			}
			for (int j = i + 1; j < itemsMovable.length; j++) {
				Forces.action(itemsMovable[i], itemsMovable[j], t);
			}
			itemsMovable[i].verification(t);
			itemsMovable[i].setNewPos(t);
			itemsMovable[i].getVitesse(t);
			itemsMovable[i].reinitialiserForce();
		}
	}

	/**
	 * Returns an array of all the Item
	 * 
	 * @return Returns an array of all the Item
	 *
	 * @see Item
	 */
	public Item[] getAllItems() {
		Item[] allItems = new Item[itemsMovable.length + items.length];
		for (int i = 0; i < itemsMovable.length; i++) {
			allItems[i] = itemsMovable[i];
		}
		for (int i = 0; i < items.length; i++) {
			allItems[i + itemsMovable.length] = items[i];
		}
		return allItems;
	}

	/**
	 * Returns the array of Items to which forces apply
	 * 
	 * @return Returns the array of Items to which forces apply
	 *
	 * @see Item
	 */
	public Item[] getItemsMovable() {
		return itemsMovable;
	}

	/**
	 * Remove item
	 * 
	 * @param itemDelete item to delete
	 * @see Item
	 */
	public void removeItem(Item itemDelete) {
		if (itemDelete.movable()) {
			Item[] newItemsMovable = new Item[itemsMovable.length - 1];
			int compteur = 0;
			for (int j = 0; j < itemsMovable.length; j++) {
				if (itemsMovable[j] != itemDelete) {
					newItemsMovable[compteur++] = itemsMovable[j];
				}
			}
			itemsMovable = newItemsMovable;
		} else {
			Item[] newItems = new Item[items.length - 1];
			int compteur = 0;
			for (int j = 0; j < items.length; j++) {
				if (items[j] != itemDelete) {
					newItems[compteur++] = items[j];
				}
			}
			items = newItems;
		}
	}

	/**
	 * Check if the class contains a Launcher
	 * 
	 * @return If the class contains a Launcher
	 * @see Launcher
	 */
	public boolean hasLauncher() {
		for (int i = 0; i < getAllItems().length; i++) {
			if (getAllItems()[i] instanceof Launcher) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add link
	 */
	public void addLien() {
		Item[] items = this.getAllItems();
		Item i;
		for (int x = 0; x < items.length; x++) {
			i = items[x];
			if (i.getLien() != null) {
				this.addItem(i.getLien());
			}
			if (i.getLienChaine() != null) {
				for (int y = 1; y < i.getLienChaine().size(); y++) {
					this.addItem(i.getLienChaine().get(y));
				}
			}
		}
	}

	/**
	 * Check if the class contains a DefeatZone
	 * 
	 * @return If the class contains a DefeatZone
	 * @see DefeatZone
	 */
	public boolean hasDefeatZone() {
		for (int i = 0; i < getAllItems().length; i++) {
			if (getAllItems()[i] instanceof DefeatZone) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove Ball
	 * 
	 * @see Ball
	 */
	public void removeBall() {
		for (int i = 0; i < getAllItems().length; i++) {
			if (getAllItems()[i] instanceof Ball) {
				removeItem(getAllItems()[i]);
			}
		}
	}
}
