1) make animations that only play once (mine, axe) -> observer sucks. Only for player, so player class should have a state machine
2) fix the npc hit box bug / next player position bug
3) snapshot needs updating whenever movement occurs
4) changes to the navigation data: should be a data container, providing routes to the outside. Route stored locally
5) add hit boxes back into the game (in the Frame class)


NEXT ON THE LIST:
inventory and its related classes
player and its related classes
reset the delta of an item when changing indices
fix extremely annoying bug that resets back to the original activity, but not direction!
big issue: pressing an animation, like mining, will only work if there's at least 1 object. should obviously always work
on e way to fix this issue would be to create a hidden game object that represents the player's hit point location, and always pass this down
this makes sure it always triggers

