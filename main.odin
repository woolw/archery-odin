package main

import "core:c/libc"
import "core:fmt"
import "core:log"
import "core:math/rand"
import "core:mem"
import "core:path/filepath"
import "core:strings"
import "vendor:raylib"

WINDOW_WIDTH :: 1050
WINDOW_HEIGHT :: 540
FPS_LIMIT :: 120

PLAYER_SPEED :: 200
LOADED_ARROW_X :: 215
LOADED_ARROW_Y_OFFSET :: 85
ARROW_SPEED :: 200

main :: proc() {
	when ODIN_DEBUG {
		context.logger = log.create_console_logger()

		track: mem.Tracking_Allocator
		mem.tracking_allocator_init(&track, context.allocator)
		context.allocator = mem.tracking_allocator(&track)

		defer {
			if len(track.allocation_map) > 0 {
				fmt.eprintf("=== %v allocations not freed: ===\n", len(track.allocation_map))
				for _, entry in track.allocation_map {
					fmt.eprintf("- %v bytes @ %v\n", entry.size, entry.location)
				}
			} else {
				fmt.eprint("=== all allocations freed ===\n")
			}

			if len(track.bad_free_array) > 0 {
				fmt.eprintf("=== %v incorrect frees: ===\n", len(track.bad_free_array))
				for entry in track.bad_free_array {
					fmt.eprintf("- %p @ %v\n", entry.memory, entry.location)
				}
			} else {
				fmt.eprint("=== no incorrect frees ===\n")
			}
			mem.tracking_allocator_destroy(&track)
		}
	}

	game()
}

balloon :: struct {
	pos:    raylib.Vector2,
	speed:  f32,
	sprite: u8,
}

player :: struct {
	pos:         raylib.Vector2,
	is_idle:     bool,
	holds_arrow: bool,
}

game :: proc() {
	raylib.InitWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "archery")
	defer raylib.CloseWindow()
	raylib.SetTargetFPS(FPS_LIMIT)

	assets := populate_assets()
	defer clear_assets(assets)

	balloons: [20]balloon
	for &b in balloons {
		b = {
			pos    = {550 + f32(rand.float32() * 450), f32(rand.float32() * WINDOW_HEIGHT)},
			speed  = (rand.float32() * 120 + 60),
			sprite = u8(rand.float32() * 5),
		}
	}
	player := player {
		pos         = {150, 20},
		is_idle     = true,
		holds_arrow = false,
	}
	arrows: [10]raylib.Vector2
	for &a, i in arrows {
		a = {20, f32(WINDOW_HEIGHT - 20 * (i + 1))}
	}
	score: u16

	for !raylib.WindowShouldClose() {
		dT := raylib.GetFrameTime()

		raylib.BeginDrawing()
		defer raylib.EndDrawing()

		raylib.ClearBackground(raylib.WHITE)
		raylib.DrawTexture(assets["assets/background.png"], 0, 0, raylib.WHITE)

		{ /* player actions */}
		{
			raylib.DrawTexture(
				assets[player.is_idle ? "assets/kenny_idle.png" : "assets/kenny_action.png"],
				i32(player.pos.x),
				i32(player.pos.y),
				raylib.WHITE,
			)

			if raylib.IsKeyDown(.S) && player.pos.y < WINDOW_HEIGHT - 170 {
				player.pos.y += PLAYER_SPEED * dT
			} else if raylib.IsKeyDown(.W) && player.pos.y > 20 {
				player.pos.y -= PLAYER_SPEED * dT
			}

			if raylib.IsKeyPressed(.SPACE) {
				player.is_idle = !player.is_idle
				if player.is_idle {
					player.holds_arrow = false
				}
			}
		}

		{ /* balloons actions */}
		{
			for &b in balloons {
				raylib.DrawTexture(
					assets[fmt.tprintf("assets/balloon_%d.png", b.sprite)],
					i32(b.pos.x),
					i32(b.pos.y),
					raylib.WHITE,
				)
				b.pos.y += b.speed * dT

				if b.pos.y > WINDOW_HEIGHT {b.pos = {550 + f32(rand.float32() * 450), -50}}
			}
		}

		{ /* arrow actions */}
		{
			#reverse for &a, i in arrows {
				if a.x > WINDOW_WIDTH {continue}
				raylib.DrawTexture(assets["assets/arrow.png"], i32(a.x), i32(a.y), raylib.WHITE)

				if a.x > LOADED_ARROW_X {
					a.x += ARROW_SPEED * dT
				} else if a.x == LOADED_ARROW_X {
					a.y = player.pos.y + LOADED_ARROW_Y_OFFSET
					if !player.holds_arrow {
						a.x += ARROW_SPEED * dT
					}
				} else if !player.is_idle && !player.holds_arrow {
					a = {LOADED_ARROW_X, player.pos.y + LOADED_ARROW_Y_OFFSET}
					player.holds_arrow = !player.holds_arrow
				}
			}
		}

		{ /* score/hit actions */}
		{
			// TODO implement hit detection
			// and scores as well as balloon reset to
			// y = -50
		}

		{ /* reset action */}
		{
			if raylib.IsKeyPressed(.R) {
				player = {
					pos         = {150, 20},
					is_idle     = true,
					holds_arrow = false,
				}
				for &a, i in arrows {
					a = {20, f32(WINDOW_HEIGHT - 20 * (i + 1))}
				}
				score = 0
			}
		}
	}
}

populate_assets :: proc() -> map[string]raylib.Texture2D {
	assets := make(map[string]raylib.Texture2D)
	matches, err := filepath.glob("assets/*.png")
	if err != nil {fmt.panicf("%s", err)}
	for match in matches {
		assets[match] = raylib.LoadTexture(fmt.ctprintf("%s", match))
	}
	delete(matches)
	return assets
}

clear_assets :: proc(assets: map[string]raylib.Texture2D) {
	for path, tex in assets {
		delete(path)
		raylib.UnloadTexture(tex)
	}
	delete(assets)
}
