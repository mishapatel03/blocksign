/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}"],
  theme: {
    extend: {
      colors: {
        background: '#0A0B0F',
        surface: '#111318',
        border: '#1E2028',
        primary: '#6366F1',
        'primary-dim': '#4F46E5',
        success: '#10B981',
        warning: '#F59E0B',
        danger: '#EF4444',
        'text-main': '#F1F5F9',
        'text-muted': '#64748B',
      },
    },
  },
  plugins: [],
}

