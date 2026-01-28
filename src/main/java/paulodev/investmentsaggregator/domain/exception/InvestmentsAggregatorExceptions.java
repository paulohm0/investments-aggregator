package paulodev.investmentsaggregator.domain.exception;

public class InvestmentsAggregatorExceptions {


    public static class BussinessRuleException extends RuntimeException {
        public BussinessRuleException(String message) {
            super(message);
        }
    }
    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }


    public static class UserNotFoundException extends EntityNotFoundException {
        public UserNotFoundException(String id) {
            super("Usuário não encontrado com ID: " + id);
        }
    }
    public static class AccountNotFoundException extends EntityNotFoundException {
        public AccountNotFoundException(String id) {
            super("Conta não encontrada com ID: " + id);
        }
    }
    public static class StockNotFoundException extends EntityNotFoundException {
        public StockNotFoundException(String id) {
            super("Ação não encontrada com ID: " + id);
        }
    }
    public static class StockAlreadyExistsException extends BussinessRuleException {
        public StockAlreadyExistsException(String ticker) {
            super("A ação " + ticker + " já está cadastrada.");
        }
    }
}
