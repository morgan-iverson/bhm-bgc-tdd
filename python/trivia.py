class TriviaGame:
    def _init_(self, questions, player_one, player_two, current_player) :
        self.questions = questions
        self.player_one = player_one
        self.player_two = player_two
        self.current_player = current_player
    
    def switch_player(self):
        if(self.current_player == self.player_one):
            self.current_player = self.player_two
        else:
            self.current_player = self.player_one

    def check_scores(self):
        print("Score Board: ")
        print("Player " + self.player_one.get_name() + ": " + self.player_one.get_score())
        print("Player " + self.player_two.get_name() + ": " + self.player_two.get_score())


    def get_game_winner(self): 
        if(self.player_one.get_score() > self.player_two.get_score()):
            return "Player " + self.player_one.get_name() + "wins!"
        else:
            return "Player " + self.player_two.get_name() + "wins!"

    def play_game(self):
        for question in self.questions:
            question.ask_question(self.current_player.get_name())
            current_player_guess = input("Player " + self.current_player.get_name() + " what is your guess?")
            correct_guess = question.is_answer_provided_correct(current_player_guess)
            if(correct_guess == True):
                self.current_player.add_point()
            
            self.switch_player()
        
        print()

class Question:
    def _init_(self, question_string, question_answer):
        self.question_string = question_string
        self.question_answer = question_answer

    def ask_question(self, player):
        print("Player " + player + " : ")
        print(self.question_string)


    def is_answer_provided_correct(self, answer_provided):
        if self.question_answer.lower().strip() == answer_provided.lower().strip():
            print("That is correct!")
            return True
        else:
            print("That is incorrect!")
            return False

class Player:
    def _init_(self, name, score):
        self.name = name
        self.score = score
    
    def get_name(self):
        return self.name
    
    def add_point(self):
        self.score+=1
    
    def get_score(self):
        return self.score
    
    


assert(sum([1, 2, 3])) == 8