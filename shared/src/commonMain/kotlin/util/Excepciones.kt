package util

class InstanceNotFoundException : Exception("Instance not found")

class IncorrectPasswordException(login: String) : Exception("Incorrect password for user: $login")

class DuplicatedLoginException(login: String) : Exception("User already exists: $login")

class InvalidGoalsException(goals: Int) : Exception("Invalid number of goals: $goals")

class StartingPlayersInvalidException() : Exception("Starting players number has to be 7")

class SubstitutesPlayersInvalidException() :
	Exception("Substitutes players number has to be 5 or less")

class InvalidEventException() : Exception("Invalid Event")

class ParsingCalendarException(calendarString: String) :
	Exception("Error parsing calendar string: $calendarString")

class InvalidMinuteException(minute: Int) : Exception("Invalid minute: $minute")

class ParsingEventListException(eventList: String) :
	Exception("Error parsing event list: $eventList")

class InvalidLanguageCodeException() : Exception("Invalid language code")

class InvalidLeagueNameException(name: String) : Exception("Invalid league name: $name")

class InvalidNumTeamsException(numTeams: Int) : Exception("Invalid number of teams: $numTeams")

class NumberFormatException(long: Long) : Exception("Error parsing long: $long")

class SameTeamException() : Exception("Home team and away team can't be the same")

class InvalidGamesPlayedException(games: Int) : Exception("Invalid number of games played: $games")

class InvalidGamesWonException(games: Int) : Exception("Invalid number of games won: $games")

class InvalidGamesDrawnException(games: Int) : Exception("Invalid number of games drawn: $games")

class InvalidGamesLostException(games: Int) : Exception("Invalid number of games lost: $games")

class InvalidYellowCardsException(cards: Int) : Exception("Invalid number of yellow cards: $cards")

class InvalidRedCardsException(cards: Int) : Exception("Invalid number of red cards: $cards")

class InvalidStringResIdException() : Exception("Invalid string resource id")

class InvalidTeamNameException(name: String) : Exception("Invalid team name: $name")

class InconsistentGamesPlayedException() : Exception("Inconsistent games played")

class InvalidNumTeamsCalendarException(numTeams: Int) :
	Exception("Invalid number of teams: $numTeams")

class InvalidTeamsFlowException() : Exception("Invalid TeamsFlow")

class InvalidCalendarMatchException() : Exception("Invalid Calendar Match")

class SharedPreferencesException() : Exception("Error with SharedPreferences")

class InvalidUserIdException(userId: Long) : Exception("Invalid user ID: $userId")

class InvalidPlayerNameException(name: String) : Exception("Invalid player name: $name")

class InvalidPlayerIdException(playerId: Long) : Exception("Invalid player id: $playerId")

class InvalidTeamIdException(teamId: Long) : Exception("Invalid team id: $teamId")

class InvalidLeagueIdException(leagueId: Long) : Exception("Invalid league id: $leagueId")

class InvalidLoginException(login: String) : Exception("Invalid login: $login")

class InvalidPasswordException() : Exception("Invalid password")

class InvalidFirstNameException(firstName: String) : Exception("Invalid first name: $firstName")

class InvalidLastNameException(lastName: String) : Exception("Invalid last name: $lastName")