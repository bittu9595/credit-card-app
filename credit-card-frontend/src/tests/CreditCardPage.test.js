import { render, screen } from "@testing-library/react";
import CreditCardPage from "../pages/CreditCardPage";

test('renders credit card page.', () => {
    render(<CreditCardPage />);
    const linkElement = screen.getByText(/Credit Card System/i);
    expect(linkElement).toBeInTheDocument();
  });