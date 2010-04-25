# LD 17: Islands (codename Envasive)
-------------

## Progress log

20100423 22:20:09 - Have applet publishing and the SVG pipeline works great, but no real solid ideas so far. Not too bad a base to build on tomorrow, though. TIME 4 SLEEP!

20100424 10:36:22 - Sick. Slept a bunch, terrifying poops. Not going to worry TOO much about finishing this thing, but going to try to at least go for my tree-killing virus-spreading two-sided story concept.

20100424 16:25:53 - No more time really invested so far today. Went to lunch with a friend, working out the sickness and playing some games. Getting out of the house now some, going to coffee shop it up and switch tracks to a different idea before getting too far at all on the first one, which was a bit too complicated for a 48 hour attempt where I've already spent half a day on other things.

20100424 16:39:19 - Wow, the compositing and blending stuff in Pulpcore is pretty awesome. 10 minutes and the first "flashlight" or "helicopter spotlight" fx are working.

20100424 16:44:07 - Crap, I don't know how to mask layers with Inkscape. Might be harder to do the cones if I can't get alpha masks.

20100424 17:28:37 - Got the Inkscape mask working but the Pulpcore export doesn't use it. Flashlight fx are mostly functioning, just need a working png for fading the flashlight cone into the circle spot.

20100424 17:32:33 - Man, this NOT turning out very object oriented. My lack of Java-touching is hurting.

20100424 17:50:47 - Okay, gimp used to get nice flashlight blend. Too much time on making it look good, but it looks GOOD! Now for some object-izing, and then MOBS!

20100424 17:59:28 - Arg, it helps if you put the new source files in src/.

20100424 18:27:52 - OO working.

20100424 21:22:29 - Firing it up again after a quality dinner with Arun.

20100424 21:36:04 - Can't get the flashlight intensity to fall off with distance correctly. Bleh.

20100424 22:05:27 - Flashlight behaves right finally, flickering more as power goes out. SWEET

20100424 22:45:25 - Settling in at home a bit, going to try to get a little UI and enemies that respond to light

20100424 23:47:41 - UI started

## TODO

Lock cursor to window?
Enemies:
  Swarmers
  "Turrets"
  Flyers?
Shaking mouse recharges flashlight
Scrollable tilemap
"is lit" check for pixel/sprite/whatever
Tutorial text
Tree/object/enemy shadows
Possibly make heli shadow visible, with spinning neat blade alpha?
Better player asset + possibly some animation, even though player is not usually visible
Crosshair/indicator for mouse cursor when flashlight is off
UI layer: Level #
Music
  OGG?
  Pacing?
  Layers?
Vsync to reduce tearing? Looked, doesn't look like it

## Vim notes

    :set makeprg=ant\ -emacs
    :set sw=4

Used this tutorial to get exuberant ctags working with Java base docs and Pulpcore API:

http://blog.vinceliu.com/2007/08/vim-tips-for-java-2-using-exuberant.html
