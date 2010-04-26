# LD 17: Crowned in Doubt (codename Envasive)
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

20100425 02:09:37 - You can now stun and kill crawling enemies for points. ALLLlllright. Also you can die and there's a game over screen! SWEET

20100425 02:24:35 - Man, there's a lot to do tomorrow. Going to have to prioritize!

20100425 10:50:24 - Back at it, slowly working flashlight shaking code in.

20100425 10:54:24 - I don't know what I was thinking, coding in mostly silence all this time! Rocking the last Buried Inside album.

20100425 11:04:18 - The ui box is ugly and stupid. Minimalizing it.

20100425 11:20:46 - Adding messages UI.

20100425 11:29:16 - Haha, I have NO IDEA how to do typed lists of tuples in Java.

20100425 11:49:23 - UI messages partly written, level abstracted, helicopter pilot will yell different things per level.

20100425 12:57:23 - Mmmm, lasagna. Probably shouldn't waste time with this, but I want to make the stunning more visceral, so time to figure out how to loop a "shakey" timeline on the stunned enemies.

20100425 14:17:16 - I can't get the freakin' flare to animate frames right! GRAHHH, skipping for now...

20100425 14:29:47 - Dang. The flare effect is too cpu intensive.

20100425 14:32:28 - Tweaking some of the CPU load.

20100425 15:44:13 - Flare clicky mechanics partly working.

20100425 16:30:53 - Crawlers pop out of the sides, and take damage from flares hopefully...

20100425 16:31:58 - Does putting quotes around the crap the helicopter pilot say make it obvious it's coming from a game entity in the sky? I HOPE SO!

20100425 16:42:57 - Testing ogg plugin. Not as easy to use as I hoped. Doh.

20100425 17:46:41 - Adding a second enemy, still no smart AI. The stupid svg pipeline won't do my awesome TEETH!

20100425 20:14:51 - Hah, went to dinner for a while and now have to super rush to get any sfx in. Music is complete, but untested!



## TODO

Enemies:
  "Turrets"
  Flyers?
  Hoppers?
Sounds
  Player hit by enemy
  Flare drop
  Enemy stunned
  Enemy death
  Player death
  Click flashlight
Better background(s)
BUG: enemies don't get unstunned when flashlight is off
enemy logic so they try to avoid light sources - A*? blah
Tree/object/enemy shadows - overlay in mask layer? too expensive?
heli light that moves randomly around slowly for first level
Options singleton for audio settings and difficulty stuff?
Scrollable tilemap for more exploration possibilities
  see if this is easy or hard with Viewport, etc. in pulpcore
make enemies slightly more confused when flashlight is off?
Possibly make heli shadow visible, with spinning neat blade alpha?
Better player asset + possibly some animation, even though player is not usually visible
Better crawler asset + animation
Crosshair/indicator for mouse cursor when flashlight is off
UI layer: Level #
Music
  OGG?
  Pacing?
  Layers?
Vsync to reduce tearing? Looked, doesn't look like it
Pickups
  Health
  Flashlight energy
  Health max
  Energy max
  Invuln
  Screen-clear
Tuples for messages + delay would be nice
Lock cursor to window?
Fullscreen?

## Vim notes

    :set makeprg=ant\ -emacs
    :set sw=4

