package main

import "core:fmt"
import "core:math/rand"
import "core:path/filepath"
import "core:strings"
import "vendor:raylib"

entity :: struct {
	pos:    raylib.Vector2,
	step:   f32,
	sprite: string,
}

balloons: [20]entity
WINDOW_HEIGHT :: 540

main :: proc() {
	raylib.InitWindow(1050, WINDOW_HEIGHT, "archery")

	assets := populate_assets()
	populate_balloons()

	raylib.SetTargetFPS(60)

	for !raylib.WindowShouldClose() {
		raylib.BeginDrawing()

		raylib.ClearBackground(raylib.WHITE)
		raylib.DrawTexture(assets["assets/background"], 0, 0, raylib.WHITE)

		for &b in balloons {
			raylib.DrawTexture(assets[b.sprite], i32(b.pos.x), i32(b.pos.y), raylib.WHITE)
			b.pos.y += f32(b.step)

			if b.pos.y > WINDOW_HEIGHT {b.pos = {550 + f32(rand.float32() * 450), -50}}
		}

		raylib.DrawFPS(0, 0)
		raylib.EndDrawing()
	}

	raylib.CloseWindow()

	clear_assets(assets)
}

populate_balloons :: proc() {
	for _, i in balloons {
		balloons[i] = {
			pos    = {550 + f32(rand.float32() * 450), f32(rand.float32() * WINDOW_HEIGHT)},
			step   = rand.float32() * 2 + 3,
			sprite = fmt.tprintf("assets/balloon_%d", int(rand.float32() * 5)),
		}
	}
}

populate_assets :: proc() -> map[string]raylib.Texture2D {
	assets := make(map[string]raylib.Texture2D)

	matches, err := filepath.glob("assets/*.png")
	if err != nil {fmt.panicf("%s", err)}

	for match in matches {
		path, _ := strings.replace_all(match, ".png", ``)
		assets[path] = raylib.LoadTexture(fmt.ctprintf("%s", match))
	}
	for m in matches {
		delete(m)
	}
	delete(matches)

	return assets
}

clear_assets :: proc(assets: map[string]raylib.Texture2D) {
	for path, tex in assets {
		fmt.println(path)
		delete(path)
		raylib.UnloadTexture(tex)
	}
	delete(assets)
}
