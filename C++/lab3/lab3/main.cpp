#include <SFML/Graphics.hpp>
#include <iostream>

#include "ControllersContext.h"
#include "Game.h"
#include "MenuController.h"
#include "StatisticController.h"
#include "OptionController.h"
#include "GameOptionController.h"
#include "GamePrepareController.h"
#include "Player1Controller.h"
#include "Player2Controller.h"
#include "EndGameController.h"

int main()
{
	sf::RenderWindow window(sf::VideoMode(1280, 720), CONSTSPACE::WINDOW_NAME);

	Game::ControllersContext context;
	Game::PlayerControllersContext p_conext;

	Game::Player1Controller *p1_c = new Game::Player1Controller(window, context);
	Game::Player2Controller *p2_c = new Game::Player2Controller(window, context);

	p_conext.set_player_controllers(p1_c, p2_c);
	context.set_player1(p1_c);
	context.set_player2(p2_c);
	Game::MenuController* m_c = new Game::MenuController(window, context);
	m_c->set_active();
	Game::StatisticController* s_c = new Game::StatisticController(window, context);
	Game::OptionController* o_c = new Game::OptionController(window, context);
	Game::GameOptionController* go_c = new Game::GameOptionController(window, context, p_conext);
	Game::GamePrepareController* gp_c = new Game::GamePrepareController(window, context, p_conext);
	Game::EndGameController* eg_c = new Game::EndGameController(window, context, p_conext);


	context.add_new_controller(Game::MenueC, m_c);
	context.add_new_controller(Game::StatisticC, s_c);
	context.add_new_controller(Game::OptionsC, o_c);
	context.add_new_controller(Game::GameOptionsC, go_c);
	context.add_new_controller(Game::GamePrepareC, gp_c);
	context.add_new_controller(Game::Player1C, p1_c);
	context.add_new_controller(Game::Player2C, p2_c);
	context.add_new_controller(Game::EndGameC, eg_c);


	Game::Game game(window, m_c);

	game.run_game();

	return 0;
}