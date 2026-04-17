package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

    @GetMapping("/")
    public String home() {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Devopsiwthaniket Game</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            min-height: 100vh;
            font-family: Arial, Helvetica, sans-serif;
            background: #111827;
            color: #f9fafb;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 24px;
        }

        main {
            width: min(920px, 100%);
        }

        h1 {
            margin: 0 0 8px;
            font-size: clamp(2rem, 8vw, 4.5rem);
            color: #38bdf8;
            text-align: center;
            letter-spacing: 0;
        }

        .follow {
            margin: 0 0 22px;
            text-align: center;
            font-size: 1.1rem;
        }

        a {
            color: #facc15;
            font-weight: 700;
        }

        .game-shell {
            border: 2px solid #38bdf8;
            border-radius: 8px;
            background: #0f172a;
            overflow: hidden;
            box-shadow: 0 18px 60px rgba(0, 0, 0, 0.35);
        }

        .scorebar {
            display: flex;
            justify-content: space-between;
            gap: 12px;
            padding: 14px 18px;
            background: #1f2937;
            font-size: 1rem;
            font-weight: 700;
        }

        #game {
            display: block;
            width: 100%;
            height: min(62vh, 520px);
            min-height: 340px;
            background: linear-gradient(#164e63, #111827);
            touch-action: none;
        }

        .controls {
            padding: 14px 18px 18px;
            color: #d1d5db;
            text-align: center;
        }

        button {
            border: 0;
            border-radius: 8px;
            background: #22c55e;
            color: #052e16;
            cursor: pointer;
            font-size: 1rem;
            font-weight: 800;
            padding: 10px 18px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <main>
        <h1>Devops with aniket</h1>
        <p class="follow">
            Follow me on Linkedin
            <a href="https://www.linkedin.com/in/aniket-devops/" target="_blank" rel="noreferrer">
                https://www.linkedin.com/in/aniket-devops/
            </a>
        </p>

        <section class="game-shell" aria-label="Pipeline catcher game">
            <div class="scorebar">
                <span>Score: <strong id="score">0</strong></span>
                <span>Lives: <strong id="lives">3</strong></span>
                <span id="message">Catch the DevOps tokens</span>
            </div>
            <canvas id="game" width="900" height="520"></canvas>
            <div class="controls">
                Move with left and right arrow keys, A/D or with mouse.
                <br>
                <button id="restart">Restart Game</button>
            </div>
        </section>
    </main>

    <script>
        const canvas = document.getElementById('game');
        const ctx = canvas.getContext('2d');
        const scoreEl = document.getElementById('score');
        const livesEl = document.getElementById('lives');
        const messageEl = document.getElementById('message');
        const restartBtn = document.getElementById('restart');

        const player = { x: 390, y: 455, width: 120, height: 24, speed: 8 };
        const keys = new Set();
        const tokens = [];
        let score = 0;
        let lives = 3;
        let frame = 0;
        let running = true;

        function resizeCanvas() {
            const rect = canvas.getBoundingClientRect();
            canvas.width = Math.floor(rect.width);
            canvas.height = Math.floor(rect.height);
            player.y = canvas.height - 64;
            player.x = Math.min(player.x, canvas.width - player.width);
        }

        function resetGame() {
            score = 0;
            lives = 3;
            frame = 0;
            running = true;
            tokens.length = 0;
            player.x = Math.max(20, (canvas.width - player.width) / 2);
            messageEl.textContent = 'Catch the DevOps tokens';
            updateHud();
        }

        function updateHud() {
            scoreEl.textContent = score;
            livesEl.textContent = lives;
        }

        function spawnToken() {
            const labels = ['CI', 'CD', 'K8s', 'Git', 'Ops'];
            tokens.push({
                x: Math.random() * (canvas.width - 52) + 20,
                y: -40,
                size: 38,
                speed: 2.4 + Math.min(score / 60, 3.8),
                label: labels[Math.floor(Math.random() * labels.length)]
            });
        }

        function movePlayer() {
            if (keys.has('ArrowLeft') || keys.has('a')) {
                player.x -= player.speed;
            }
            if (keys.has('ArrowRight') || keys.has('d')) {
                player.x += player.speed;
            }
            player.x = Math.max(0, Math.min(canvas.width - player.width, player.x));
        }

        function updateTokens() {
            for (let i = tokens.length - 1; i >= 0; i--) {
                const token = tokens[i];
                token.y += token.speed;

                const hitPlayer =
                    token.x < player.x + player.width &&
                    token.x + token.size > player.x &&
                    token.y < player.y + player.height &&
                    token.y + token.size > player.y;

                if (hitPlayer) {
                    score += 10;
                    messageEl.textContent = 'Nice catch, Devopsiwthaniket!';
                    tokens.splice(i, 1);
                    continue;
                }

                if (token.y > canvas.height) {
                    lives--;
                    messageEl.textContent = lives > 0 ? 'Keep the pipeline alive' : 'Game over';
                    tokens.splice(i, 1);
                }
            }

            if (lives <= 0) {
                running = false;
            }
            updateHud();
        }

        function drawBackground() {
            ctx.fillStyle = '#111827';
            ctx.fillRect(0, 0, canvas.width, canvas.height);

            ctx.strokeStyle = 'rgba(56, 189, 248, 0.18)';
            ctx.lineWidth = 1;
            for (let x = 0; x < canvas.width; x += 48) {
                ctx.beginPath();
                ctx.moveTo(x, 0);
                ctx.lineTo(x, canvas.height);
                ctx.stroke();
            }
            for (let y = 0; y < canvas.height; y += 48) {
                ctx.beginPath();
                ctx.moveTo(0, y);
                ctx.lineTo(canvas.width, y);
                ctx.stroke();
            }
        }

        function drawPlayer() {
            ctx.fillStyle = '#22c55e';
            ctx.fillRect(player.x, player.y, player.width, player.height);
            ctx.fillStyle = '#f9fafb';
            ctx.font = 'bold 14px Arial';
            ctx.textAlign = 'center';
            ctx.fillText('DevOps', player.x + player.width / 2, player.y + 17);
        }

        function drawTokens() {
            tokens.forEach((token) => {
                ctx.fillStyle = '#facc15';
                ctx.fillRect(token.x, token.y, token.size, token.size);
                ctx.fillStyle = '#111827';
                ctx.font = 'bold 14px Arial';
                ctx.textAlign = 'center';
                ctx.fillText(token.label, token.x + token.size / 2, token.y + 24);
            });
        }

        function drawGameOver() {
            ctx.fillStyle = 'rgba(17, 24, 39, 0.78)';
            ctx.fillRect(0, 0, canvas.width, canvas.height);
            ctx.fillStyle = '#f9fafb';
            ctx.font = 'bold 34px Arial';
            ctx.textAlign = 'center';
            ctx.fillText('Game Over', canvas.width / 2, canvas.height / 2 - 18);
            ctx.font = '18px Arial';
            ctx.fillText('Press Restart Game to play again', canvas.width / 2, canvas.height / 2 + 24);
        }

        function loop() {
            drawBackground();
            movePlayer();

            if (running) {
                frame++;
                if (frame % 48 === 0) {
                    spawnToken();
                }
                updateTokens();
            }

            drawTokens();
            drawPlayer();

            if (!running) {
                drawGameOver();
            }

            requestAnimationFrame(loop);
        }

        window.addEventListener('keydown', (event) => keys.add(event.key));
        window.addEventListener('keyup', (event) => keys.delete(event.key));
        window.addEventListener('resize', resizeCanvas);
        canvas.addEventListener('pointermove', (event) => {
            if (event.buttons || event.pointerType === 'touch') {
                const rect = canvas.getBoundingClientRect();
                player.x = event.clientX - rect.left - player.width / 2;
                player.x = Math.max(0, Math.min(canvas.width - player.width, player.x));
            }
        });
        restartBtn.addEventListener('click', resetGame);

        resizeCanvas();
        resetGame();
        loop();
    </script>
</body>
</html>
        """;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
